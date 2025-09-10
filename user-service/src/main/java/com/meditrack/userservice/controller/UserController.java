package com.meditrack.userservice.controller;

import com.meditrack.userservice.model.User;
import com.meditrack.userservice.repo.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repo;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserController(UserRepository repo) { this.repo = repo; }

    @GetMapping public List<User> list() { return repo.findAll(); }

    @PostMapping public User create(@RequestBody User u) {
        if (u.getPassword() != null) u.setPassword(encoder.encode(u.getPassword()));
        return repo.save(u);
    }

    @GetMapping("/{id}") public ResponseEntity<User> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        return repo.findByUsername(username).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
