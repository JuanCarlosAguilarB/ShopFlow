package auth_service.product.services.find;

import auth_service.product.domain.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // for Mockito manager the mocks
public class ProductFinderShould {

//    @BeforeEach
//    public void setUp() {
//        ProductRepository repository = Mockito.mock(ProductRepository.class);
//        ProductFinder finder = new ProductFinder(repository);
//    }
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductFinder finder;


    @Test
    public void should_be_found_a_available_product() {
//        ProductRepository repository = Mockito.mock(ProductRepository.class);
//        ProductFinder finder = new ProductFinder(repository);

        UUID productId = UUID.randomUUID();
        when(repository.IsAvailable(productId)).thenReturn(Mono.just(true));
//        result.block(); // wait for the result

        Mono<Boolean> result = finder.IsAvailable(productId);

        StepVerifier.create(result)
                .expectNext(true)
                .verifyComplete();

        verify(repository, times(1)).IsAvailable(productId);
    }


    @Test
    public void should_be_found_a_unavailable_product() {
//        ProductRepository repository = Mockito.mock(ProductRepository.class);
//        ProductFinder finder = new ProductFinder(repository);

        UUID productId = UUID.randomUUID();
        when(repository.IsAvailable(productId)).thenReturn(Mono.just(false));

        Mono<Boolean> result = finder.IsAvailable(productId);

        StepVerifier.create(result)
                .expectNext(false)
                .verifyComplete();

        verify(repository, times(1)).IsAvailable(productId);
    }
}
