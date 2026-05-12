package com.example.LibraryAPI.service;

import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public String generateToken(String username){
        return "fake-jwt-token-for-" + username;
    }
    
}
