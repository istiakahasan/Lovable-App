package com.example.lovable_App.dto.member;

import com.example.lovable_App.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(@NotNull ProjectRole role) {
}
