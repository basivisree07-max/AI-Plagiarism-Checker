package com.plagiarism.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.plagiarism.dto.LoginRequest;
import com.plagiarism.dto.LoginResponse;
import com.plagiarism.dto.RegisterRequest;
import com.plagiarism.model.User;
import com.plagiarism.repository.UserRepository;
import com.plagiarism.security.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // Constructor
    public AuthService(UserRepository userRepository,
                       JwtService jwtService) {

        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // =========================
    // REGISTER
    // =========================

    public User register(RegisterRequest request) {

        // Check whether email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // Encrypt password
        String encryptedPassword =
                passwordEncoder.encode(request.getPassword());

        // Create User object
        User user = new User(
                request.getName(),
                request.getEmail(),
                encryptedPassword,
                request.getRole()
        );

        // Save user in MongoDB
        return userRepository.save(user);
    }


    // =========================
    // LOGIN
    // =========================

    public LoginResponse login(LoginRequest request) {

        // Find user by email
        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // Check password
        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        // If password is wrong
        if (!passwordMatches) {
            throw new RuntimeException("Invalid password");
        }

        // Generate JWT token
        String token =
                jwtService.generateToken(
                        user.getEmail(),
                        user.getRole()
                );

        // Return login response
        return new LoginResponse(
                token,
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}