package com.example.lovable_App.service.impl;

import com.example.lovable_App.dto.auth.AuthResponse;
import com.example.lovable_App.dto.auth.LoginRequest;
import com.example.lovable_App.dto.auth.SignupRequest;
import com.example.lovable_App.entity.User;
import com.example.lovable_App.error.BadRequestException;
import com.example.lovable_App.mapper.UserMapper;
import com.example.lovable_App.repository.UserRepository;
import com.example.lovable_App.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Service
public class AuthServiceImpl implements AuthService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse signup(SignupRequest request) {
        userRepository.findByUsername(request.username()).ifPresent(uesr->{
            throw new BadRequestException("User already exists with username"+request.username());
        });

        User user=userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        user =userRepository.save(user);


        return new AuthResponse("dummy",userMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
