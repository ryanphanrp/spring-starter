package ryan.phan.starter.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ryan.phan.starter.constant.common.ResponseCode;
import ryan.phan.starter.dto.auth.AuthUser;
import ryan.phan.starter.dto.user.CreateUserDto;
import ryan.phan.starter.dto.user.UserDto;
import ryan.phan.starter.entity.User;
import ryan.phan.starter.exception.GlobalAppException;
import ryan.phan.starter.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public UserDto createUser(CreateUserDto dto) {
        User entity = dto.toEntity();
        repository.save(entity);
        return new UserDto(entity);
    }

    public List<UserDto> list() {
        return repository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    public UserDto getDetail(String username) {
        User user = repository.findUserByUsername(username).orElseThrow(() -> new GlobalAppException(ResponseCode.NOT_FOUND_USER));
        return new UserDto(user);
    }

    @Override
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findUserByUsername(username)
                .orElseThrow(() -> new GlobalAppException(ResponseCode.NOT_FOUND_USER));
        return new AuthUser(user);
    }
}
