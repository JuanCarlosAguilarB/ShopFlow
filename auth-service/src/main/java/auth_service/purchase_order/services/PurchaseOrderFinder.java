package auth_service.purchase_order.services;

import auth_service.purchase_order.domain.PurchaseOrder;
import auth_service.purchase_order.domain.PurchaseOrderRepository;
import auth_service.purchase_order.domain.PurchaseOrdersResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@AllArgsConstructor
@Service
public class PurchaseOrderFinder {

    private final PurchaseOrderRepository repository;

    public Mono<PurchaseOrdersResponse> findAll() {

//        return repository.findAll().collect(Collectors.toList()).map(
//                orders -> new OrderResponse(orders.stream().toList())
//        );

        // refactor
//        return repository.findAll() //
//                .collectList() // this method return a  Mono<List<PurchaseOrder>>
//                .map(OrderResponse::new);

        // returning response or mono empty when we get an error
        return repository.findAll() //
                .collectList() // this method return a  Mono<List<PurchaseOrder>>
                .map(PurchaseOrdersResponse::new)
                .doOnError(
//                        throwable -> Mono.error(throwable)
                        error -> Mono.empty()
                );
    }

    public Mono<PurchaseOrder> findById(UUID purchaseOrderId) {
        return repository.findById(purchaseOrderId)
                .switchIfEmpty(
                        Mono.error(new Exception("Purchase Order Not Found.")) // TODO: make a domain exceptions
                );
    }
}
