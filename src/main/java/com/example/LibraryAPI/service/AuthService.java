package com.example.LibraryAPI.service;
import org.springframework.stereotype.Service;

import com.example.LibraryAPI.dto.LoginRequest;
import com.example.LibraryAPI.dto.TokenResponse;

@Service
public class AuthService {
    private final JwtService jwtService;

    public AuthService(JwtService jwtService){
        this.jwtService = jwtService;
    }
    
    public TokenResponse login(LoginRequest request){
        if(!request.getUsername().equals("admin") ||
            !request.getPassword().equals("password")){
                throw new RuntimeException("Invalid credentials");
            }

            String token = jwtService.generateToken(request.getUsername());

            return new TokenResponse(token);
    }
}
