package auth_service.payment.services.create;

import auth_service.payment.domain.Payment;
import auth_service.payment.domain.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentCreator {

    private final PaymentRepository repository;

    public Mono<Void> create(UUID purchaseOrderId) {
        return repository.save(Payment.builder()
                .id(UUID.randomUUID())
                .purchaseOrderId(purchaseOrderId)
                .wasSuccessful(true)
                .build()).then(Mono.empty());
    }
}
