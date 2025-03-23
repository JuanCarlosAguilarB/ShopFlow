package auth_service.product.infrastructure;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface R2DBProductRepository extends ReactiveCrudRepository<ProductEntity, UUID> {
}
