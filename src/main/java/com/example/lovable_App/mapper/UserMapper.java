package com.example.lovable_App.mapper;

import com.example.lovable_App.dto.auth.SignupRequest;

import com.example.lovable_App.dto.auth.UserProfileResponse;
import com.example.lovable_App.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignupRequest signupRequest);

    UserProfileResponse toUserProfileResponse(User user);
}
