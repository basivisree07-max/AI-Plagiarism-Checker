package com.plagiarism.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProtectedController {

    @GetMapping("/api/protected")
    public String protectedEndpoint() {
        return "JWT Authentication Successful! This is a protected API.";
    }
}