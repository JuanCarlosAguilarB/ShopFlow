package auth_service.user.persistence;

import auth_service.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@AllArgsConstructor
@Table("user_entity")
@Data
public class UserEntity {

    @Id
    private UUID id;
    private String username;
    private String name;

    public static UserEntity fromDomain(User user) {
        return new UserEntity(user.getId(), user.getUsername(), user.getName());
    }
}



