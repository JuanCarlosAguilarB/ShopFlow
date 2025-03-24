package auth_service.payment.services.create;

import auth_service.order.domain.PurchaseOrder;
import auth_service.order.service.find.PurchaseOrderFinder;
import auth_service.order.service.validate.PurchaseOrderValidator;
import auth_service.payment.domain.Payment;
import auth_service.payment.domain.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

//1) dont works

//@Service
//@AllArgsConstructor
//public class PaymentCreator {
//
//    private final PaymentRepository repository;
//    private final PurchaseOrderValidator purchaseOrderValidator;
//    private final PurchaseOrderFinder  purchaseOrderFinder;
//
//    public Mono<Void> create(UUID purchaseOrderId,  BigDecimal amount) {
//
//        // verify products are available
//        Mono<Boolean> productsAreAvailable = purchaseOrderValidator.allProductsAreAvailable(purchaseOrderId);
//
//        if (!productsAreAvailable){
//            return Mono.error(new Exception("Product not available"));
//        }
//
//        // verify the price of order is equal to payment
//        Mono<PurchaseOrder>  purchaseOrder = purchaseOrderFinder.findById(purchaseOrderId);
//        purchaseOrder
//                .filter(order -> order.getTotal().subtract(amount).compareTo(amount)==0)
//                .switchIfEmpty(Mono.error(new Exception("Payment and value of order amount do not match")));
//
//
//        return repository.save(Payment.builder()
//                .id(UUID.randomUUID())
//                .purchaseOrderId(purchaseOrderId)
//                .wasSuccessful(true)
//                .amount(amount.doubleValue())
//                .build()).then(Mono.empty());
//    }
//}

//------------------------------------------------------------------------------------------------------------------------

@Service
@AllArgsConstructor
public class PaymentCreator {

    private final PaymentRepository repository;
    private final PurchaseOrderValidator purchaseOrderValidator;
    private final PurchaseOrderFinder  purchaseOrderFinder;

    public Mono<Void> create(UUID purchaseOrderId, Double amount) {
        // execute two task in parallel that not have dependencies between them
        Mono<Boolean> productsAvailable = purchaseOrderValidator.allProductsAreAvailable(purchaseOrderId);
        Mono<PurchaseOrder> purchaseOrder = purchaseOrderFinder.findById(purchaseOrderId);

        // Mono.zip allows you to run the two operations in parallel and then combine them into a single stream.
        return Mono.zip(productsAvailable, purchaseOrder)
                .flatMap(tuple -> {
                    boolean productsAreAvailable = tuple.getT1();
                    PurchaseOrder order = tuple.getT2();

                    if (!productsAreAvailable) {
                        return Mono.error(new Exception("Product not available"));
                    }

                    if (order.getTotal().compareTo(amount) != 0) {
                        return Mono.error(new Exception("Payment and value of order amount do not match"));
                    }

                    return repository.save(
                            Payment.builder()
                            .id(UUID.randomUUID())
                            .purchaseOrderId(purchaseOrderId)
                            .wasSuccessful(true)
                            .amount(amount.doubleValue())
                            .build())
                        .then();
                });
    }
}

// -------------------------------------
// all tas in one flow

//@Service
//@AllArgsConstructor
//public class PaymentCreator {
//
//    private final PaymentRepository repository;
//    private final PurchaseOrderValidator purchaseOrderValidator;
//    private final PurchaseOrderFinder purchaseOrderFinder;
//
//    public Mono<Void> create(UUID purchaseOrderId, BigDecimal amount) {
//
//        return purchaseOrderValidator.allProductsAreAvailable(purchaseOrderId)
//                .flatMap(productsAvailable -> {
//                    if (!productsAvailable) {
//                        return Mono.error(new Exception("Product not available"));
//                    }
//
//                    return purchaseOrderFinder.findById(purchaseOrderId)
//                            .filter(order -> order.getTotal().compareTo(amount) == 0)
//                            .switchIfEmpty(Mono.error(new Exception("Payment and value of order amount do not match")))
//
//                            .flatMap(order -> repository.save(Payment.builder()
//                                    .id(UUID.randomUUID())
//                                    .purchaseOrderId(purchaseOrderId)
//                                    .wasSuccessful(true)
//                                    .amount(amount.doubleValue())
//                                    .build()))
//                            .then(Mono.empty());
//                });
//    }
//}