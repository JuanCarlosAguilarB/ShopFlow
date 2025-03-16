package auth_service.order;

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
    public Mono<ResponseEntity<?>> save(@RequestBody Order order) {

//        service.save(order);
//        return Mono.just(ResponseEntity.ok().build());

        // fixing the wrong management of asynchronis
        // now, we wait for ending of the save method
        return service.save(order)
                .then(Mono.just(ResponseEntity.ok().build()));
    }

    @GetMapping("api/v1/orders")
    public Mono<ResponseEntity<OrdersResponse>> findOrders() {
        return finderService.findOrders()
                .map(order -> ResponseEntity.ok().body(order))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
