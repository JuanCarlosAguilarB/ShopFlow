package auth_service.user.persistence;

import auth_service.user.domain.User;
import auth_service.user.domain.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@AllArgsConstructor
@Slf4j
@Repository
public class RDBUserRepository  implements UserRepository {

    private final R2dbcEntityTemplate template;
    private DatabaseClient databaseClient;

    @Override
    public Mono<String> findUsername(UUID id) {
        log.info("findUsername id in repository : {}", id);
//        return template.selectOne(query(where("id").is(id)), UserEntity.class).map(UserEntity::getUsername);

        // template dont allow to get just an one field
        return databaseClient.sql("SELECT username FROM users WHERE id = $1")
                .bind("$1", id)
                .map((row, metadata) -> row.get("username", String.class))
                .one();
    }

    @Override
    public Mono<Void> save(User user) {
        log.info("save user in repository : {}", user);
        return template.insert(UserEntity.class)
                .using(UserEntity.fromDomain(user))
                .then();
    }
}
