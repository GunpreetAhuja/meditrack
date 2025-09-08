package com.meditrack.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static Map<Long, Map<String, Object>> store = new HashMap<>();
    private static long seq = 1;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    static {
        Map<String, Object> u = new HashMap<>();
        u.put("id", seq);
        u.put("username", "alice");
        u.put("password", "$2a$10$u1qQZ0r6yZ0pQJ9oYzdP9eZx1fJq2G1y8d3sKQ8mZ6Y8eFh1qOa2u"); // bcrypt("password")
        u.put("roles", Arrays.asList("PATIENT"));
        store.put(seq, u);
        seq++;
    }

    @GetMapping
    public Collection<Map<String, Object>> list() { return store.values(); }

    @PostMapping
    public Map<String, Object> create(@RequestBody Map<String, Object> body) {
        long id = seq++;
        if (body.containsKey("password")) {
            String raw = body.get("password").toString();
            body.put("password", passwordEncoder.encode(raw));
        }
        body.put("id", id);
        store.put(id, body);
        return body;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> get(@PathVariable Long id) {
        Map<String, Object> u = store.get(id);
        if (u == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(u);
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<Map<String, Object>> getByUsername(@PathVariable String username) {
        for (Map<String, Object> u : store.values()) {
            if (username.equals(u.get("username"))) {
                return ResponseEntity.ok(u);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
