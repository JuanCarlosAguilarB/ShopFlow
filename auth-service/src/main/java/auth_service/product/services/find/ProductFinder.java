package auth_service.product.services.find;

import auth_service.product.domain.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductFinder {
    private final ProductRepository repository;

    public Mono<Boolean>  IsAvailable(UUID productId) {
        return  repository.IsAvailable(productId);
    }
}
