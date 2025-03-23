package auth_service.product.domain;

import java.util.UUID;
import reactor.core.publisher.Mono;

public interface ProductRepository {

    Mono<Boolean> IsAvailable(UUID productId);
    
}
