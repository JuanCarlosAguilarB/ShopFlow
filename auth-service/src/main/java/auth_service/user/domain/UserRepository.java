package auth_service.user.domain;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository {
    Mono<String>  findUsername(UUID id);  // no
    Mono<Void> save(User user);
}
