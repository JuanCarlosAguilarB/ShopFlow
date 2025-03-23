package auth_service.product.infrastructure;

import auth_service.product.domain.ProductRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class RDProductRepository implements ProductRepository {

    @Override
    public Mono<Boolean> IsAvailable(UUID productId) {
        return Mono.just(true);
    }
}
