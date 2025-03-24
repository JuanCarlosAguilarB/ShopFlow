package auth_service.payment.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Payment {

    private UUID id;
    private UUID purchaseOrderId;
    private boolean wasSuccessful;
    private LocalDateTime createdAt;
}
