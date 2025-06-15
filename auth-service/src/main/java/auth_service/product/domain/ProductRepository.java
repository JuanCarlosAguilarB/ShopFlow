package auth_service.product.domain;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ProductRepository {

    Mono<Boolean> IsAvailable(UUID productId);

    Mono<Void> save(Product product);

    Mono<Product> FindById(UUID productId);

    Mono<List<Product>> FindAll(Optional<Integer> page, Optional<Integer> limit, Optional<Integer> offset);
}
