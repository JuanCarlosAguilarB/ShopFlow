package auth_service.order.infrastructure.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseOrderEntityRepository extends ReactiveCrudRepository<PurchaseOrderEntity, UUID> {
}
