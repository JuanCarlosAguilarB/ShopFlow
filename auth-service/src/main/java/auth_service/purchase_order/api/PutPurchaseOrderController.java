package auth_service.purchase_order.api;

import auth_service.purchase_order.services.PurchaseOrderCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@Slf4j
@AllArgsConstructor
public class PutPurchaseOrderController {

    public final PurchaseOrderCreator executor;

    @PutMapping("v1/purchases/{id}")
    public Mono<ResponseEntity<?>> updatePurchaseOrder(@RequestBody PurchaseOrderRequest request,  @PathVariable UUID id) {
        log.info("PutPurchaseController - request = {}", request);
        return executor.create(
                id,
                request.getUserId(),
                request.getOrderNumber()
                ).map(response -> ResponseEntity.ok().build());
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class PurchaseOrderRequest {
    private UUID userId;
    private String orderNumber;
}
