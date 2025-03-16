package auth_service.order;

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
