package auth_service.historical_product_price.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class HistoricalProductPrice {
    private double price;
    private UUID productId;
    private LocalDateTime createdAt;
}
