package auth_service.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderFinder {

    private OrderRepository repository;

    public Mono<OrderResponse> findOrders() {
        return repository.findAll().collect(Collectors.toList()).map(
                orders -> new OrderResponse(orders.stream().toList())
        );
    }
}