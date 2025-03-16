package auth_service.user;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository {
    Mono<String>  findUsername(UUID id);  // no
}
