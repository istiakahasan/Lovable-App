package com.example.lovable_App.dto.member;

import com.example.lovable_App.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(Long userId,
                             String email,
                             String name,
                             ProjectRole projectRole,
                             Instant invitedAt) {
}
