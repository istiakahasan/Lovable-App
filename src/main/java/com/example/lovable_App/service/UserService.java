package com.example.lovable_App.service;

import com.example.lovable_App.dto.auth.UserProfileResponse;


public interface UserService {



     UserProfileResponse getProfile(Long userId);
}
