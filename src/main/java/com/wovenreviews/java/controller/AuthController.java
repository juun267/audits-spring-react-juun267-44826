package com.wovenreviews.java.controller;

import com.wovenreviews.java.config.JwtUtils;
import com.wovenreviews.java.dto.*;
import com.wovenreviews.java.model.User;
import com.wovenreviews.java.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        return userRepository.findByEmail(loginRequest.getEmail())
                .filter(user -> encoder.matches(loginRequest.getPassword(), user.getPassword()))
                .map(user -> {
                    String token = jwtUtils.generateJwtToken(user.getEmail());
                    return ResponseEntity
                            .ok(new UserAuthResponse(
                                    new UserResponse(user.getEmail(), user.getFirstName(), user.getLastName()),
                                    token));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                Instant.now());

        userRepository.save(user);

        String token = jwtUtils.generateJwtToken(user.getEmail());

        return ResponseEntity.ok(new UserAuthResponse(
                new UserResponse(user.getEmail(), user.getFirstName(), user.getLastName()),
                token));
    }
}
