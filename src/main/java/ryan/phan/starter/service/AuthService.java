package ryan.phan.starter.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ryan.phan.starter.dto.auth.AuthUser;
import ryan.phan.starter.dto.auth.LoginDto;
import ryan.phan.starter.dto.auth.LoginResponseDto;
import ryan.phan.starter.utils.JwtUtils;

@Service
public class AuthService {

    final AuthenticationManager authenticationManager;

    public AuthService(AuthenticationManager auth) {
        this.authenticationManager = auth;
    }

    public LoginResponseDto login(LoginDto dto) {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));     // Xác thực từ username và password.
        SecurityContextHolder.getContext().setAuthentication(authentication);   // Set thông tin authentication vào Security Context
        final AuthUser user = (AuthUser) authentication.getPrincipal(); // Get username
        String jwt = JwtUtils.generate(user.getUsername()); // Trả về jwt cho người dùng.
        return new LoginResponseDto(jwt);
    }
}
