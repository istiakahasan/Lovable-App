package com.example.lovable_App.service.impl;

import com.example.lovable_App.dto.auth.UserProfileResponse;
import com.example.lovable_App.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }
}
