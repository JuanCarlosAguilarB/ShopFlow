package auth_service.payment.domain;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PaymentRepository {
    Mono<Boolean> wasPaid(UUID productId);
}
