package ryan.phan.starter.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import ryan.phan.starter.entity.User;

@Data
@NoArgsConstructor
public class UserDto {
    private Long Id;
    private String name;
    private String username;

    public UserDto(User user) {
        this.Id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
    }
}
