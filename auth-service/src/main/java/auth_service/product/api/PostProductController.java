package auth_service.product.api;

import auth_service.product.domain.Product;
import auth_service.product.services.create.ProductCreator;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@Tag(name="Products")
public class PostProductController {

    private final ProductCreator productCreator;

    @PostMapping("v1/products/")
    public Mono<ResponseEntity<Product>> create(@RequestBody ProductRequest request) {

        return productCreator.create(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getQuantity()
                ).map(product -> ResponseEntity.ok().body(product));
    }
}

@Data
class ProductRequest {
    private String name;
    private String description;
    private double price;
    private int quantity;
}
