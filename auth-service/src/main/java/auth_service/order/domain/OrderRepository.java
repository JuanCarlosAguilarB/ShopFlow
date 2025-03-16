package auth_service.order.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderRepository {
    Mono<Void> save(Order order);
    Flux<Order> findAll();
}
