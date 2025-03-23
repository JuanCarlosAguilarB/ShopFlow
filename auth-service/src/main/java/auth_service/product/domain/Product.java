package auth_service.product.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Product {

    private UUID id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private LocalDateTime createdAt;
}
