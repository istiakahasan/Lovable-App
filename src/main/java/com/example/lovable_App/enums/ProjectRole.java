package com.example.lovable_App.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.security.Permission;
import java.util.Set;

import static com.example.lovable_App.enums.ProjectPermission.*;


@RequiredArgsConstructor
@Getter
public enum ProjectRole {

    EDITOR(Set.of(VIEW,EDIT,DELETE,VIEW_MEMBERS)),
    VIEWER(Set.of(VIEW,VIEW_MEMBERS)),
    OWNER(Set.of(VIEW, EDIT,DELETE,MANAGE_MEMBERS,VIEW_MEMBERS));

    private final Set<ProjectPermission> permissions;
}
