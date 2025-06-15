package auth_service.product.api;

import auth_service.product.services.find.ProductFinder;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@RestController
@Tag(name="Products")
@AllArgsConstructor
public class GetProductController {

    private final ProductFinder productFinder;

    @GetMapping("v1/products/{id}")
    public Mono<ResponseEntity<?>> FindProduct(@RequestParam UUID id) {
        return productFinder
                .FindById(id)
                .map(ResponseEntity::ok);
    }

    @GetMapping("v1/products/")
    public Mono<ResponseEntity<?>> FindAllProducts(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> offset
//            Filter filters
    ) {
        return productFinder.FindAll(page, limit, offset).map(products->ResponseEntity.ok(products));
    }


}
