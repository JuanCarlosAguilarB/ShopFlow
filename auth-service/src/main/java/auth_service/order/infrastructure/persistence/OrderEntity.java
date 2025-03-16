package auth_service.order.infrastructure.persistence;

import auth_service.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("orders")
public class OrderEntity {
    private UUID id;
    private UUID userId;

    @Column("created_at")
    private LocalDate createdAt;

    @Column("delivery_date")
    private LocalDate deliveryDate;
    private BigDecimal total;
    private Boolean status;

    public static OrderEntity fromDomain(Order order) {
        return new OrderEntity(
                order.getId(),
                order.getUserId(),
                order.getCreatedAt(),
                order.getDeliveryDate(),
                order.getTotal(),
                order.getStatus()
        );
    }
}
