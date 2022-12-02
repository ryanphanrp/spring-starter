package ryan.phan.starter.dto.user;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ryan.phan.starter.anotation.RoleValidation;
import ryan.phan.starter.constant.Role;
import ryan.phan.starter.entity.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateUserDto {
    @NotEmpty(message = "Username must not be empty.")
    @NotNull(message = "Username is required")
    String username;

    @NotEmpty(message = "Name must not be empty.")
    String name;

    @NotEmpty(message = "Password must not be empty.")
    @NotNull(message = "Password is required.")
    @Size(min = 8, message = "Password has {min} characters at least!")
    String password;

    @RoleValidation(message = "Cant found this role.")
    Role role = Role.USER;

    public User toEntity(){
        User entity = new User();
        entity.setUsername(this.username);
        entity.setPassword(new BCryptPasswordEncoder().encode(this.password));
        entity.setName(this.name);
        entity.setRole(this.role);
        return entity;
    }
}
