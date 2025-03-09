package auth_service.todo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

public interface TodoService {
    Mono<Todo> findTodoById(int id) throws TodoNotFoundException, ExecutionException, InterruptedException;
    Mono<Todo> searchTodoByTitle(String title);
    Flux<Todo> findAllTodos();
    Mono<Todo> saveTodo(Todo todo);
    Mono<Void> deleteTodoById(int id);
}