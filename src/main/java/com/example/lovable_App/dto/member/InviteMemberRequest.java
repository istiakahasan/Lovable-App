package com.example.lovable_App.dto.member;

import com.example.lovable_App.enums.ProjectRole;

public record InviteMemberRequest(String email,
                                  ProjectRole role) {
}
