package auth_service.payment.infrastructure.persistence;

import auth_service.payment.domain.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@AllArgsConstructor
public class RDPaymentRepository implements PaymentRepository {

    private final PaymentEntityRepository repository;

    @Override
    public Mono<Boolean> wasPaid(UUID productId) {
        return repository.findById(productId)
                .map(PaymentEntity::getWasSuccessful);
    }
}
