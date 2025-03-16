package auth_service.order.api;

import auth_service.order.domain.Order;
import auth_service.order.service.OrderCreator;
import auth_service.order.service.OrderFinder;
import auth_service.order.service.OrderResponse;
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
        service.save(order);
        return Mono.just(ResponseEntity.ok().build());
    }

    @GetMapping("api/v1/orders")
    public Mono<ResponseEntity<OrderResponse>> findById() {
        return finderService.findOrders()
                .map(order -> ResponseEntity.ok().body(order))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
