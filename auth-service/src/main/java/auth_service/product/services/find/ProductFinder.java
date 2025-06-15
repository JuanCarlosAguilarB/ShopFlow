package auth_service.product.services.find;

import auth_service.product.domain.ProductRepository;
import auth_service.product.domain.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductFinder {
    
    private final ProductRepository repository;

    public Mono<Boolean>  IsAvailable(UUID productId) {
        return  repository.IsAvailable(productId);
    }


    public Mono<ProductResponse> FindById(UUID productId) {
        return repository.FindById(productId).map(product ->
                new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getQuantity(),
                        product.getCreatedAt()
                )
        );
    }

    public Mono<List<ProductResponse>> FindAll(Optional<Integer> page, Optional<Integer> limit, Optional<Integer> offset) {

        return repository.FindAll(page, limit, offset)
                .map(products -> products.stream().map(product ->
                    new ProductResponse(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getQuantity(),
                            product.getCreatedAt()
                    )
                ).toList()
        );
    }
}
