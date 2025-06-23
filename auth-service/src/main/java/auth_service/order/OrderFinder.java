package auth_service.order;

import auth_service.purchase_order.domain.PurchaseOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class OrderFinder {

    private PurchaseOrderRepository repository;

    public Mono<OrdersResponse> findOrders() {

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
                .map(OrdersResponse::new)
                .doOnError(
//                        throwable -> Mono.error(throwable)
                    error -> Mono.empty()
                );
    }
}