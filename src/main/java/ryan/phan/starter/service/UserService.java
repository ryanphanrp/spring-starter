package ryan.phan.starter.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ryan.phan.starter.dto.auth.AuthUser;
import ryan.phan.starter.dto.user.CreateUserDto;
import ryan.phan.starter.dto.user.UserDto;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto createUser(CreateUserDto dto);
    List<UserDto> list();
    UserDto getDetail(String username);
    AuthUser loadUserByUsername(String username) throws UsernameNotFoundException;
}
