package auth_service.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private UUID id;
    private UUID userId;
    private LocalDate createdAt;
    private LocalDate deliveryDate;
    private BigDecimal total;
    private Boolean status;

}
