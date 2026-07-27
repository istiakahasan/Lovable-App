package com.example.lovable_App.controller;

import com.example.lovable_App.dto.auth.AuthResponse;
import com.example.lovable_App.dto.auth.LoginRequest;
import com.example.lovable_App.dto.auth.SignupRequest;
import com.example.lovable_App.dto.auth.UserProfileResponse;
import com.example.lovable_App.service.AuthService;
import com.example.lovable_App.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthController {

     AuthService authservice;
     UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest request){
    return ResponseEntity.ok(authservice.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authservice.login(request));
    }
    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile(){
        Long userId=1L;
        return ResponseEntity.ok(userService.getProfile(userId));

    }
}
