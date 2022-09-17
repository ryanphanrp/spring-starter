package ryan.phan.starter.dto.auth;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String jwt;

    public LoginResponseDto(String jwt) {
        this.jwt = jwt;
    }
}
