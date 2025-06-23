package auth_service.purchase_order.infrastructure.persistence;

import auth_service.purchase_order.domain.PurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("purchase_order")
public class PurchaseOrderEntity {

    @Id
    private UUID id;
    private UUID userId;

    @Column("created_at")
    private LocalDate createdAt;

    @Column("delivery_date")
    private LocalDate deliveryDate;
    private Double total;

    private String orderNumber;
    private LocalDate updatedAt;
    private Boolean wasClosed;
    private Boolean wasCancelled;


    public static PurchaseOrderEntity fromDomain(PurchaseOrder purchaseOrder) {
        return new PurchaseOrderEntity(
                purchaseOrder.getId(),
                purchaseOrder.getUserId(),
                purchaseOrder.getCreatedAt(),
                purchaseOrder.getDeliveryDate(),
                purchaseOrder.getTotal(),
                purchaseOrder.getOrderNumber(),
                purchaseOrder.getUpdatedAt(),
                purchaseOrder.getWasClosed(),
                purchaseOrder.getWasCancelled()
        );
    }

    public static PurchaseOrder toDomain(PurchaseOrderEntity entity){
        return new PurchaseOrder(
                entity.getId(),
                entity.getUserId(),
                entity.getCreatedAt(),
                entity.getDeliveryDate(),
                entity.getTotal(),
                entity.getOrderNumber(),
                entity.getUpdatedAt(),
                entity.getWasClosed(),
                entity.getWasCancelled()
        );
    }
}
