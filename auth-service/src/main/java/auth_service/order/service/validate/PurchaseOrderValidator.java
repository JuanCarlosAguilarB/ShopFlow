package auth_service.order.service.validate;

import auth_service.order.domain.PurchaseOrderRepository;
import auth_service.order.service.find.PurchaseOrderFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

//@Service
//@AllArgsConstructor
//public class PurchaseOrderValidator {
//
//    private final PurchaseOrderRepository repository;
//    private final PurchaseOrderFinder finder;
//
//    public Mono<Boolean> productsAreAvailable(UUID purchaseOrderId) {
//        return finder.findById(purchaseOrderId)
//                .switchIfEmpty(Mono.error(new Exception("purchase order not exist")))
//                .doOnTerminate(() -> repository.productsAreAvailable(purchaseOrderId));
//    }
//}

@Service
@AllArgsConstructor
public class PurchaseOrderValidator {

    private final PurchaseOrderRepository repository;
    private final PurchaseOrderFinder finder;

    public Mono<Boolean> allProductsAreAvailable(UUID purchaseOrderId) {
        return finder.findById(purchaseOrderId)
                .switchIfEmpty(Mono.error(new Exception("Purchase order does not exist")))
                .flatMap(purchaseOrder -> repository.allProductsAreAvailable(purchaseOrderId)
                        .map(isAvailable -> isAvailable)
                );
    }
}