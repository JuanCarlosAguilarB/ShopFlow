package auth_service.user.api;

import auth_service.user.services.UsersCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@Slf4j
@AllArgsConstructor
public class PutUserController {

    private final UsersCreator usersCreator;

    @PutMapping("v1/users/{id}")
    public Mono<ResponseEntity<?>> save(@PathVariable UUID id, @RequestBody UserRequest request) {
        log.info("PUT /api/v1/users/{id}", id);
        return usersCreator.create(id, request.getName(), request.getUsername())
                .map(response -> ResponseEntity.ok().build());
    }

}

@Data
class UserRequest {

    private String name;
    private String username;

}
