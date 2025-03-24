package auth_service.order;

import auth_service.order.domain.PurchaseOrder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderCreator service;
    private final OrderFinder finderService;

    @PostMapping("api/v1/orders")
    public Mono<ResponseEntity<?>> save(@RequestBody PurchaseOrder purchaseOrder) {

//        service.save(purchaseOrder);
//        return Mono.just(ResponseEntity.ok().build());

        // fixing the wrong management of asynchronis
        // now, we wait for ending of the save method
        return service.save(purchaseOrder)
                .then(Mono.just(ResponseEntity.ok().build()));
    }

    @GetMapping("api/v1/orders")
    public Mono<ResponseEntity<OrdersResponse>> findOrders() {
        return finderService.findOrders()
                .map(order -> ResponseEntity.ok().body(order))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
