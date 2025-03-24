package auth_service.order.infrastructure.persistence;

import auth_service.order.domain.PurchaseOrder;
import auth_service.order.domain.PurchaseOrderRepository;
import auth_service.order.OrderResponse;
import auth_service.order.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class RDPurchasePurchaseOrderRepository implements PurchaseOrderRepository {

    private final PurchaseOrderEntityRepository repository;

    // to make joins we can use DatabaseClient or R2dbcEntityTemplate
    private final DatabaseClient databaseClient;

    @Override
    public Mono<Void> save(PurchaseOrder purchaseOrder) {
        PurchaseOrderEntity purchaseOrderEntity = PurchaseOrderEntity.fromDomain(purchaseOrder);
        System.out.println(purchaseOrderEntity);
        // Se suscribe explícitamente para forzar la ejecución (esto no es recomendable para producción)
        repository.save(purchaseOrderEntity)
                .doOnTerminate(() -> System.out.println("La operación de guardado ha terminado"))
                .doOnError(error -> System.out.println("Error al guardar: " + error.getMessage()))
                .subscribe();  // Suscripción al flujo
        return Mono.empty();
    }

    @Override
    public Flux<OrderResponse> findAll() {

//        return repository.findAll().map(PurchaseOrderEntity::toDomain);
//        return Flux.just(new PurchaseOrder(), new PurchaseOrder()); // for testing, i didnt work with data


        String query = "SELECT o.id AS orderId, o.created_at AS createdAt, o.delivery_date AS deliveryDate, " +
                "o.total, o.status, u.username, u.id AS userId " +
                "FROM orders o " +
                "JOIN user_entity u ON o.user_id = u.id";

        return databaseClient.sql(query)
                .map((row, metadata) -> new OrderResponse(
                        row.get("orderId", UUID.class),
                        new UserResponse(row.get("userId", UUID.class),row.get("username", String.class)),
                        row.get("createdAt", LocalDate.class),
                        row.get("deliveryDate", LocalDate.class),
                        row.get("total", BigDecimal.class),
                        row.get("status", Boolean.class)
                ))
                .all();  // Return the Flux of OrderResponse
    }

}
