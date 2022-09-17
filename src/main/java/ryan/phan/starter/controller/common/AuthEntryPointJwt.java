package ryan.phan.starter.controller.common;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ryan.phan.starter.constant.common.ResponseCode;
import ryan.phan.starter.dto.common.ResponseDto;
import ryan.phan.starter.utils.JSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        final PrintWriter out = response.getWriter();
        out.print(JSONUtils.stringify(new ResponseDto<>(ResponseCode.UNAUTHORIZED)));
        out.flush();
    }
}
