package com.example.lovable_App.service;

import com.example.lovable_App.dto.auth.AuthResponse;
import com.example.lovable_App.dto.auth.LoginRequest;
import com.example.lovable_App.dto.auth.SignupRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
