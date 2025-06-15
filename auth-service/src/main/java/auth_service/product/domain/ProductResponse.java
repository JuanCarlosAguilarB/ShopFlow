package auth_service.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@AllArgsConstructor
@Getter
@Setter
public class ProductResponse {

    private UUID id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private LocalDate createdAt;


}
