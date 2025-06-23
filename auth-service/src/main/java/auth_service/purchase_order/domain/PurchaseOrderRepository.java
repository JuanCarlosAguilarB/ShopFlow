package auth_service.purchase_order.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PurchaseOrderRepository {
    Mono<Void> save(PurchaseOrder purchaseOrder);
    Flux<PurchaseOrderResponse> findAll();
    Mono<Boolean> allProductsAreAvailable(UUID purchaseOrderId);
    Mono<PurchaseOrder> findById(UUID purchaseOrderId);
}
