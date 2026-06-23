package com.example.lovable_App.service.impl;

import com.example.lovable_App.dto.auth.AuthResponse;
import com.example.lovable_App.dto.auth.LoginRequest;
import com.example.lovable_App.dto.auth.SignupRequest;
import com.example.lovable_App.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponse signup(SignupRequest request) {
        return null;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
