package auth_service.order.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface R2DBCOrderRepository extends ReactiveCrudRepository<OrderEntity, UUID> {
}
