package auth_service.payment.services.find;

import auth_service.payment.domain.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentFinder {

    private final PaymentRepository repository;


    Mono<Boolean> wasPaid(UUID productId) {
        return repository.wasPaid(productId);
    }
}
