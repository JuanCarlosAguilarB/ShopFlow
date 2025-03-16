package auth_service.user.persistence;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@AllArgsConstructor
@Table("user_entity")
public class UserEntity {

    @Id
    private UUID id;
    private String username;

}



