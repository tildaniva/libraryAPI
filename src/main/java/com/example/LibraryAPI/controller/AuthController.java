package com.example.LibraryAPI.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibraryAPI.dto.LoginRequest;
import com.example.LibraryAPI.dto.TokenResponse;
import com.example.LibraryAPI.service.AuthService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public TokenResponse login (@RequestBody LoginRequest request){
        return authService.login(request);
    }
}
