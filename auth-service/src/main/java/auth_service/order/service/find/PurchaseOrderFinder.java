package auth_service.order.service.find;

import auth_service.order.domain.PurchaseOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@AllArgsConstructor
@Service
public class PurchaseOrderFinder {
    public Mono<PurchaseOrder> findById(UUID purchaseOrderId) {
        return Mono.just(new PurchaseOrder());
    }
}
