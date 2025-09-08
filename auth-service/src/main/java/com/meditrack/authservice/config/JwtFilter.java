package com.meditrack.authservice.config;

import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, jakarta.servlet.ServletException {
        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (auth != null && auth.startsWith("Bearer ")) {
            String token = auth.substring(7);
            try {
                Claims claims = jwtUtil.parseToken(token).getBody();
                Object roles = claims.get("roles");
                List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_PATIENT"));
                if (roles instanceof List) {
                    authorities = ((List<?>) roles).stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.toString())).collect(Collectors.toList());
                }
                Authentication authentication = new PreAuthenticatedAuthenticationToken(claims.getSubject(), token, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                // invalid token - continue without auth
            }
        }
        filterChain.doFilter(request, response);
    }
}
