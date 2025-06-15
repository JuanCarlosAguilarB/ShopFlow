package auth_service.product.services.create;

import auth_service.product.domain.Product;
import auth_service.product.domain.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductCreator {

    private final ProductRepository productRepository;

    public Mono<Product> create(String name, String description, double price, int quantity) {
        Product product = Product.create(UUID.randomUUID(), name, description, price, quantity);
        return productRepository.save(product).thenReturn(product);
    }
}
