package auth_service.order.service;

import auth_service.order.domain.Order;
import auth_service.order.domain.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class OrderCreator {
    private final OrderRepository repository;

    public Mono<Void> save(Order order){
         repository.save(order);
         return Mono.empty();
    }
}
