package auth_service.order.infrastructure.persistence;

import auth_service.order.domain.Order;
import auth_service.order.domain.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Repository
public class RDBOrderRepository implements OrderRepository {

    private final R2DBCOrderRepository repository;

    @Override
    public Mono<Void> save(Order order) {
        OrderEntity orderEntity = OrderEntity.fromDomain(order);
        System.out.println(orderEntity);
        // Se suscribe explícitamente para forzar la ejecución (esto no es recomendable para producción)
        repository.save(orderEntity)
                .doOnTerminate(() -> System.out.println("La operación de guardado ha terminado"))
                .doOnError(error -> System.out.println("Error al guardar: " + error.getMessage()))
                .subscribe();  // Suscripción al flujo
        return Mono.empty();
    }

    @Override
    public Flux<Order> findAll() {
//        return repository.findAll().map(orderEntity -> OrderRepository::fromDomain);
        return Flux.just(new Order(), new Order());
    }
}
