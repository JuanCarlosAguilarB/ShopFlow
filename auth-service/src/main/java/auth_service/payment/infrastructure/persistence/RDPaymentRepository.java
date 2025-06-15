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
                // you have to remember ReactiveCrudRepository.save() makes a update when id!=null and insert in other case
                // but, if we have a @id annotation un the r2dbc entity, r2dbc make assumption that the db will generate the Id.
                .id(payment.getId()==null ? UUID.randomUUID() : payment.getId())
                .purchaseOrderId(payment.getPurchaseOrderId())
                .wasSuccessful(payment.getWasSuccessful())
                .createdAt(payment.getCreatedAt())
                .amount(payment.getAmount())
                .build())
                .then(Mono.empty());
    }
}
