package ryan.phan.starter.dto.auth;

import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ryan.phan.starter.constant.Role;

import java.util.Collections;
import java.util.Objects;

@Setter
public class AuthUser extends User {
    private final Long id;
    private final String username;
    private final Role role;

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, getUsername(), role);
    }

    public AuthUser(Long id, String username, String password, Role role) {
        super(username, password, Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.toString())));
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public AuthUser(ryan.phan.starter.entity.User user) {
        this(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthUser)) return false;
        if (!super.equals(o)) return false;
        AuthUser authUser = (AuthUser) o;
        return id.equals(authUser.id) && getUsername().equals(authUser.getUsername()) && role == authUser.role;
    }
}
