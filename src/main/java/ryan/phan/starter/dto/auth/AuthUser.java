package ryan.phan.starter.dto.auth;

import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ryan.phan.starter.constant.Role;

import java.util.Collections;

@Setter
public class AuthUser extends User {
    private final Long Id;
    private final String username;
    private final Role role;

    public AuthUser(Long Id, String username, String password, Role role) {
        super(username, password, Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.toString())));
        this.Id = Id;
        this.username = username;
        this.role = role;
    }

    public AuthUser(ryan.phan.starter.entity.User user) {
        this(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }
}
