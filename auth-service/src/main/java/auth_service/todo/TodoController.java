package auth_service.todo;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Todo by id.")
    Mono<ResponseEntity<Todo>> findTodoById(@PathVariable int id) throws ExecutionException, InterruptedException, TodoNotFoundException {
        return todoService.findTodoById(id)
                .map(todo -> ResponseEntity.ok(todo));
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all Todos")
    Flux<Todo> findAllTodos() {
        return todoService.findAllTodos();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Todo.")
    Mono<ResponseEntity<Todo>> saveTodo(@RequestBody Todo todo) {


        return todoService.saveTodo(todo)
                .map(savedTodo -> ResponseEntity.status(HttpStatus.CREATED).body(savedTodo));
//        return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(todo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Todo by id.")
    Mono<Void> deleteTodoById(@PathVariable int id) {
        return todoService.deleteTodoById(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Search Todo by title.")
    Mono<ResponseEntity<Todo>> searchTodoByTitle(@RequestParam String title) {
        return todoService.searchTodoByTitle(title)
                .map(todo -> ResponseEntity.ok(todo))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

//    @GetMapping("/{id}")
//    Mono<SuccessDataResponse<Todo>> findTodoById(@PathVariable int id) throws TodoNotFoundException, ExecutionException, InterruptedException {
//        // Custom Response
//        return todoService.findTodoById(id).map(todo -> new SuccessDataResponse<>(todo));
//    }

}