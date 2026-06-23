package com.example.lovable_App.dto.project;

import com.example.lovable_App.dto.auth.UserProfileResponse;

import java.time.Instant;

public record ProjectSummaryResponse(Long id, String projectName, Instant createdAt,
                                     Instant updatedAt, UserProfileResponse owner) {
}
