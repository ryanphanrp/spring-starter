package ryan.phan.starter.security.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ryan.phan.starter.dto.auth.AuthUser;
import ryan.phan.starter.service.UserService;
import ryan.phan.starter.utils.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    final UserService userService;
    public JwtAuthFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            // Get JWT token from request
            String token = extractJwt(request);
            if (JwtUtils.validate(token)) {
                // Get username from Jwt token
                String username = JwtUtils.getUsernameFromJWT(token);

                // Validate username and Authentication Context
                if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    // Get information's user from username
                    AuthUser authUser = userService.loadUserByUsername(username);

                    // If user's information is valid, set them into Authentication Security Context
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwt(HttpServletRequest request) {
        final String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header)) {
            return header.startsWith("Bearer ") ? header.substring(7) : header;
        }
        return null;
    }
}
