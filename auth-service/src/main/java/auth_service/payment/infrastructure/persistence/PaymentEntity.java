package auth_service.payment.infrastructure.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Double amount;


    public Boolean getWasSuccessful() {
        return wasSuccessful;
    }
}
