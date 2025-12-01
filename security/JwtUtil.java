
package com.example.onlinecourse.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration-ms}")
    private Long expirationMs;

    public String generateToken(String username) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(Algorithm.HMAC256(secret.getBytes()));
    }

    public String validateAndGetUsername(String token) {
        DecodedJWT decoded = JWT.require(Algorithm.HMAC256(secret.getBytes()))
                .build().verify(token);
        return decoded.getSubject();
    }
}
