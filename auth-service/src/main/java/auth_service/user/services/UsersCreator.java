package auth_service.user.services;

import auth_service.user.domain.User;
import auth_service.user.domain.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class UsersCreator {

    private final UserRepository userRepository;

    public Mono<Void> create(UUID id, String name, String username) {

        return userRepository.save(
                new User(id, name, username)
        );

    }
}
