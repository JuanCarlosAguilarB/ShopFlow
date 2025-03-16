package auth_service.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class OrderCreator {
    private final OrderRepository repository;

    public Mono<Void> save(Order order){
         return repository.save(order);

         // if we want to work with any async process after that response, we could use  doOnTerminate, doOnSuccess or doOnError, for example
//        return repository.save(order)
//                .doOnSuccess(aVoid -> {
//                    // this was executed after the order was saved
//                    System.out.println("Order saved successfully!");
//                })
//                .doOnError(e -> {
//                    // in case of error
//                    System.err.println("Error saving order: " + e.getMessage());
//                });
    }
}
