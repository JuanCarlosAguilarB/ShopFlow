package auth_service.payment.infrastructure.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentEntity {
    private UUID id;
    private UUID purchaseOrderId;
    private boolean wasSuccessful;
    private LocalDateTime createdAt;

    public Boolean getWasSuccessful() {
        return wasSuccessful;
    }
}
