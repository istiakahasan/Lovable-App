package com.example.lovable_App.dto.project;

import com.example.lovable_App.dto.auth.UserProfileResponse;

import java.time.Instant;

public record ProjectResponse(Long id,
                              String name,
                              Instant createdAt,
                              Instant updatedAt,
                              UserProfileResponse owner) {

}
