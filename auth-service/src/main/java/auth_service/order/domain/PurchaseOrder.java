package auth_service.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {

    private UUID id;
    private UUID userId;
    private LocalDate createdAt;
    private LocalDate deliveryDate;
    private Double total;

    private String orderNumber;
    private LocalDate updatedAt;
    // TODO: when an payment was made
    private Boolean wasClosed;
    // TODO: an order can be cancelled when the payment was not made or the time exceeded 15 days and the payment was not made
    private Boolean wasCancelled;
}
