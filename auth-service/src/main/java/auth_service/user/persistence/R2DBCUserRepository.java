package auth_service.user.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface R2DBCUserRepository extends ReactiveCrudRepository<UserEntity, UUID> {
}
