package auth_service.payment.infrastructure.persistence;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentEntity {
    private UUID id;
    private UUID purchaseOrderId;
    private boolean wasSuccessful;
    private LocalDateTime createdAt;

    public Boolean getWasSuccessful() {
        return wasSuccessful;
    }
}
