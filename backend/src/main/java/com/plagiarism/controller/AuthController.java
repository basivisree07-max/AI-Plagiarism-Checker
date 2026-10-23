package com.plagiarism.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plagiarism.dto.LoginRequest;
import com.plagiarism.dto.LoginResponse;
import com.plagiarism.dto.RegisterRequest;
import com.plagiarism.dto.RegisterResponse;
import com.plagiarism.model.User;
import com.plagiarism.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // Constructor
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // =========================
    // REGISTER
    // =========================

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        User user = authService.register(request);
        return new RegisterResponse(user.getId(), user.getName(),
                user.getEmail(), user.getRole());
    }


    // =========================
    // LOGIN
    // =========================

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return authService.login(request);
    }
}
