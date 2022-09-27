package ryan.phan.starter.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import ryan.phan.starter.entity.User;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String username;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
    }
}
