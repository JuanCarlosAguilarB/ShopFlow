package auth_service.purchase_order.api;

import auth_service.purchase_order.domain.PurchaseOrdersResponse;
import auth_service.purchase_order.services.PurchaseOrderFinder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class GetPurchaseOrderController {

    private final PurchaseOrderFinder finderService;


    @GetMapping("api/v1/orders")
    public Mono<ResponseEntity<PurchaseOrdersResponse>> findOrders() {
        return finderService.findAll()
                .map(order -> ResponseEntity.ok().body(order))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
