package auth_service.payment.Api;

import auth_service.payment.services.create.PaymentCreator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class PostController {

    private final PaymentCreator paymentCreator;

    @PostMapping("/payments")
    public Mono<ResponseEntity> ConfirmPayment(PaymentRequest paymentRequest) {
        // we will supose that this is a payment confirmation, so we will create a payment

        return paymentCreator.create(paymentRequest.purchaseOrderId(), paymentRequest.amount())
                .doAfterTerminate(() -> log.info("Payment request received: {}", paymentRequest))
                .doOnTerminate(() -> log.info("Payment created"))
                .then(Mono.just(ResponseEntity.ok().build()));
    }
}


record PaymentRequest(
//        String paymentId, String paymentMethod, String paymentStatus
    UUID purchaseOrderId,
    Double amount
) {
}
