package auth_service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/productos")
@Tag(name = "Productos", description = "Operaciones sobre productos")
public class ProductoController {

//    private final ProductoService service;
//
//    public ProductoController(ProductoService service) {
//        this.service = service;
//    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos")
    public Flux<String> obtenerTodos() {
        return Flux.fromIterable(List.of("s"));
    }

//    @GetMapping("/{id}")
//    @Operation(summary = "Obtener un producto por ID")
//    public Mono<Producto> obtenerPorId(@PathVariable String id) {
//        return service.obtenerPorId(id);
//    }
//
//    @PostMapping
//    @Operation(summary = "Guardar un nuevo producto")
//    public Mono<Producto> guardar(@RequestBody Producto producto) {
//        return service.guardar(producto);
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Eliminar un producto por ID")
//    public Mono<Void> eliminar(@PathVariable String id) {
//        return service.eliminar(id);
//    }
}