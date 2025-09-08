package com.meditrack.authservice.controller;

import com.meditrack.authservice.config.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final RestTemplate restTemplate = new RestTemplate();
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestParam String username, @RequestParam String password) {
        String url = "http://user-service:8092/api/users/by-username/" + username;
        try {
            Map user = restTemplate.getForObject(url, Map.class);
            if (user == null || !user.containsKey("password")) {
                return ResponseEntity.status(401).build();
            }
            String hashed = user.get("password").toString();
            if (!passwordEncoder.matches(password, hashed)) {
                return ResponseEntity.status(401).build();
            }
            Map<String,Object> claims = new HashMap<>();
            Object roles = user.get("roles");
            if (roles instanceof List) {
                claims.put("roles", roles);
            } else if (roles != null) {
                claims.put("roles", List.of(roles.toString()));
            } else {
                claims.put("roles", List.of("PATIENT"));
            }
            String token = jwtUtil.generateToken(username, claims);
            Map<String,String> resp = new HashMap<>();
            resp.put("token", token);
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
}
