package com.meditrack.userservice.controller;

import com.meditrack.userservice.model.User;
import com.meditrack.userservice.repo.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> list() { return userRepository.findAll(); }

    @PostMapping
    public User create(@RequestBody User u) {
        if (u.getPassword() != null) {
            u.setPassword(passwordEncoder.encode(u.getPassword()));
        }
        return userRepository.save(u);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        return userRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
