package auth_service.order;

import auth_service.purchase_order.domain.PurchaseOrder;
import auth_service.purchase_order.domain.PurchaseOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class OrderCreator {
    private final PurchaseOrderRepository repository;

    public Mono<Void> save(PurchaseOrder purchaseOrder){
         return repository.save(purchaseOrder);

         // if we want to work with any async process after that response, we could use  doOnTerminate, doOnSuccess or doOnError, for example
//        return repository.save(purchaseOrder)
//                .doOnSuccess(aVoid -> {
//                    // this was executed after the purchaseOrder was saved
//                    System.out.println("PurchaseOrder saved successfully!");
//                })
//                .doOnError(e -> {
//                    // in case of error
//                    System.err.println("Error saving purchaseOrder: " + e.getMessage());
//                });
    }
}
