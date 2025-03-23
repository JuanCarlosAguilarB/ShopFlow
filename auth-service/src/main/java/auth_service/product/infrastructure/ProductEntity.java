package auth_service.product.infrastructure;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Data
public class ProductEntity {

    @Id
    private UUID id;
    
    private String name;
    private String description;
    private double price;
    private int quantity;
    private LocalDateTime createdAt;

}
