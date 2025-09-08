package com.meditrack.authservice.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${JWT_SECRET:}")
    private String jwtSecret;

    private Key key() {
        if (jwtSecret != null && !jwtSecret.isBlank()) {
            return Keys.hmacShaKeyFor(jwtSecret.getBytes());
        } else {
            return Keys.secretKeyFor(SignatureAlgorithm.HS256);
        }
    }

    private final long validityMs = 3600_000; // 1h

    public String generateToken(String username, Map<String, Object> claims) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .addClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validityMs))
                .signWith(key())
                .compact();
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
    }
}
