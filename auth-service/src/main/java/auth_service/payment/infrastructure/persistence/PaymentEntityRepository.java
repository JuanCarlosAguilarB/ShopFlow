package auth_service.payment.infrastructure.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import java.util.UUID;

public interface PaymentEntityRepository extends ReactiveCrudRepository<PaymentEntity, UUID> {
}
