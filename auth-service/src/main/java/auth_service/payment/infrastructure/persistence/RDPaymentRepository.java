package auth_service.payment.infrastructure.persistence;

import auth_service.payment.domain.Payment;
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

    @Override
    public Mono<Void> save(Payment payment) {
        return repository.save(PaymentEntity.builder()
                .id(payment.getId())
                .purchaseOrderId(payment.getPurchaseOrderId())
                .wasSuccessful(payment.getWasSuccessful())
                .createdAt(payment.getCreatedAt())
                .build())
                .then(Mono.empty());
    }
}
