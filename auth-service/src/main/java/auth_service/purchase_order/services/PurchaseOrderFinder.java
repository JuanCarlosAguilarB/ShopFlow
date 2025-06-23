package auth_service.purchase_order.services;

import auth_service.purchase_order.domain.PurchaseOrder;
import auth_service.purchase_order.domain.PurchaseOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@AllArgsConstructor
@Service
public class PurchaseOrderFinder {

    private final PurchaseOrderRepository repository;

    public Mono<PurchaseOrder> findById(UUID purchaseOrderId) {
        return repository.findById(purchaseOrderId)
                .switchIfEmpty(
                        Mono.error(new Exception("Purchase Order Not Found.")) // TODO: make a domain exceptions
                );
    }
}
