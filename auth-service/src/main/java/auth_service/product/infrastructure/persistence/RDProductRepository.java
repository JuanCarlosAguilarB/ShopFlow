package auth_service.product.infrastructure.persistence;

import auth_service.product.domain.Product;
import auth_service.product.domain.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
@AllArgsConstructor
public class RDProductRepository implements ProductRepository {

    private final ProductEntityRepository productEntityRepository;

    // we're going to use R2dbcEntityTemplate due to we are generating the ids from back, and ReactiveCrudRepository doesn't allow it
    private final R2dbcEntityTemplate template;

    @Override
    public Mono<Boolean> IsAvailable(UUID productId) {
        return Mono.just(true);
    }

    @Override
    public Mono<Void> save(Product product) {
        return template.insert(ProductEntity.class)
                .using(ProductEntity.from(product))
//                .map(ProductEntity.from(product))
                .then();

//        return productEntityRepository.save(ProductEntity.from(product)).then(Mono.empty());
    }

    @Override
    public Mono<Product> FindById(UUID productId) {
        return productEntityRepository
                .findById(productId)
                .map(ProductEntity::to);
    }

    @Override
    public Mono<List<Product>> FindAll(Optional<Integer> page, Optional<Integer> limit, Optional<Integer> offset) {
        // R2dbcEntityTemplate doesn't have automatic pagination
        int pageNumber = page.orElse(0);
        int pageSize = limit.orElse(10);
        int skip = offset.orElse(pageNumber * pageSize);

        Query query = Query.empty()
                .limit(pageSize)
                .offset(skip);

        return template.select(ProductEntity.class)
                .matching(query)
                .all()
                .map(ProductEntity::to)
                .collectList();
    }
}
