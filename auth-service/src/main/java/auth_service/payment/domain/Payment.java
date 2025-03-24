package auth_service.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Payment {

    private UUID id;
    private UUID purchaseOrderId;
    private boolean wasSuccessful;
    private LocalDateTime createdAt;


    public boolean getWasSuccessful() {
        return wasSuccessful;
    }
}
