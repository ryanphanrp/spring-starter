package ryan.phan.starter.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class JwtUtils {
    @Value("${security.jwt.secret_key}")
    private String JWT_SECRET;

    @Value("${security.jwt.expiration}")
    private long JWT_EXPIRATION;

    private JwtUtils() {
    }

    public String generate(String username) {
        Date now = new Date();
        Date expiration = new Date(System.currentTimeMillis() + JWT_EXPIRATION);
        return Jwts.builder()
                .setId(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(toSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = makeJwtParser().parseClaimsJws(token).getBody();
        return claims.getId();
    }

    public boolean validate(String token) {
        if (Objects.isNull(token)) return false;
        try {
            makeJwtParser().parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty.");
        } catch (SignatureException e) {
            log.error("JWT signature is not match with locally.");
        }
        return false;
    }

    // Make JWT Parser
    private JwtParser makeJwtParser() {
        return Jwts.parserBuilder().setSigningKey(toSigningKey()).build();
    }

    // Make Sign Key with HMAC
    private Key toSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
