package ryan.phan.starter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ryan.phan.starter.constant.common.ResponseCode;
import ryan.phan.starter.dto.auth.LoginDto;
import ryan.phan.starter.dto.auth.LoginResponseDto;
import ryan.phan.starter.dto.common.ResponseDto;
import ryan.phan.starter.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseDto<LoginResponseDto> login(@RequestBody LoginDto dto){
        LoginResponseDto response = service.login(dto);
        return new ResponseDto<>(ResponseCode.OK, response);
    }
}
