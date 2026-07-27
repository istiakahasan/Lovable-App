package com.example.lovable_App.service.impl;

import com.example.lovable_App.dto.auth.UserProfileResponse;
import com.example.lovable_App.error.ResourceNotFoundException;
import com.example.lovable_App.repository.UserRepository;
import com.example.lovable_App.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    UserRepository userRepository;


    @Override
    public UserProfileResponse getProfile(Long userId) {

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User name is not found"+username));
    }
}
