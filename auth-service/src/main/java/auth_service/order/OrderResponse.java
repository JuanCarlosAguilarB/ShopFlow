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
public class OrderResponse {

    private UUID id;
    private UserResponse user;  // we can replace it with a map, because, user isn't from this domain
    private LocalDate createdAt;
    private LocalDate deliveryDate;
    private BigDecimal total;
    private Boolean status;

}
