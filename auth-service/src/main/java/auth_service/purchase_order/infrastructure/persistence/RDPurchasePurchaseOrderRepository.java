package auth_service.purchase_order.infrastructure.persistence;

import auth_service.purchase_order.domain.PurchaseOrder;
import auth_service.purchase_order.domain.PurchaseOrderRepository;
import auth_service.purchase_order.domain.PurchaseOrderResponse;
import auth_service.purchase_order.domain.UserResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Repository
@Slf4j
public class RDPurchasePurchaseOrderRepository implements PurchaseOrderRepository {

    private final PurchaseOrderEntityRepository entityRepository;

    // to make joins we can use DatabaseClient or R2dbcEntityTemplate
    private final DatabaseClient databaseClient;

    // we're going to use R2dbcEntityTemplate due to we are generating the ids from back, and ReactiveCrudRepository doesn't allow it
    private final R2dbcEntityTemplate template;

    @Override
    public Mono<Void> save(PurchaseOrder purchaseOrder) {
//        PurchaseOrderEntity purchaseOrderEntity = PurchaseOrderEntity.fromDomain(purchaseOrder);
//        System.out.println(purchaseOrderEntity);
//
//        entityRepository.save(purchaseOrderEntity)
//                .doOnTerminate(() -> System.out.println("La operación de guardado ha terminado"))
//                .doOnError(error -> System.out.println("Error al guardar: " + error.getMessage()))
//                .subscribe();  // Suscripción al flujo

        log.info("insert purchase order into repository : {}", purchaseOrder);
        return  template.insert(PurchaseOrderEntity.class)
                .using(PurchaseOrderEntity.fromDomain(purchaseOrder))
                .then(Mono.empty());
    }

    @Override
    public Flux<PurchaseOrderResponse> findAll() { // TODO: move to right repository

//        return repository.findAll().map(PurchaseOrderEntity::toDomain);
//        return Flux.just(new PurchaseOrder(), new PurchaseOrder()); // for testing, i didnt work with data


        String query = "SELECT o.id AS orderId, o.created_at AS createdAt, o.delivery_date AS deliveryDate, " +
                "o.total, o.status, u.username, u.id AS userId " +
                "FROM orders o " +
                "JOIN user_entity u ON o.user_id = u.id";

        return databaseClient.sql(query)
                .map((row, metadata) -> new PurchaseOrderResponse(
                        row.get("orderId", UUID.class),
                        new UserResponse(row.get("userId", UUID.class),row.get("username", String.class)),
                        row.get("createdAt", LocalDate.class),
                        row.get("deliveryDate", LocalDate.class),
                        row.get("total", BigDecimal.class),
                        row.get("status", Boolean.class)
                ))
                .all();  // Return the Flux of OrderResponse
    }

    @Override
    public Mono<Boolean> allProductsAreAvailable(UUID purchaseOrderId) {
        return Mono.just(true);
    }

    @Override
    public Mono<PurchaseOrder> findById(UUID id) {
        return entityRepository.findById(id)
                .map(PurchaseOrderEntity::toDomain);
    }

}
