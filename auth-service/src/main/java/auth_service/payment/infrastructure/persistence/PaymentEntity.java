package auth_service.payment.infrastructure.persistence;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {
    private UUID id;
    private UUID purchaseOrderId;
    private boolean wasSuccessful;
    private LocalDateTime createdAt;
}
