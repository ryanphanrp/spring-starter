package ryan.phan.starter.service;

import ryan.phan.starter.dto.auth.LoginDto;
import ryan.phan.starter.dto.auth.LoginResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginDto dto);
}
