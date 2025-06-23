package auth_service.purchase_order.services;

import auth_service.purchase_order.domain.PurchaseOrder;
import auth_service.purchase_order.domain.PurchaseOrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class PurchaseOrderCreator {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public Mono<Void> create(UUID id, UUID userId, String orderNumber) {

    //    TODO : search deleveryDate and total from order domain
    Double total = 0.0; // search
    LocalDate deliveryDate = LocalDate.now();

    PurchaseOrder purchaseOrder = PurchaseOrder.create(
            id,
            userId,
            orderNumber,
            deliveryDate,
            total
    );

    return purchaseOrderRepository.save(purchaseOrder);
    }
}
