package ryan.phan.starter.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ryan.phan.starter.constant.common.ResponseCode;
import ryan.phan.starter.dto.common.ResponseDto;
import ryan.phan.starter.utils.JSONUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        final PrintWriter out = response.getWriter();
        out.print(JSONUtils.stringify(new ResponseDto<>(ResponseCode.UNAUTHORIZED)));
        out.flush();
    }
}
