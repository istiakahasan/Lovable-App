package com.example.lovable_App.security;

import com.example.lovable_App.enums.ProjectPermission;
import com.example.lovable_App.enums.ProjectRole;
import com.example.lovable_App.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("security")
@RequiredArgsConstructor
public class SecurityExpressions {

    private final ProjectMemberRepository projectMemberRepository;
    private final AuthUtil authUtil;

    private boolean hasPermission(Long projectId, ProjectPermission projectPermission) {
        Long  userId=authUtil.getCurrentUserId();
        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId,userId)
                .map(role->role.getPermissions().contains(projectPermission)).orElse(false);
    }


    //Who can View this Project
    public boolean canViewProject(Long projectId){

        return hasPermission(projectId, ProjectPermission.VIEW);

//        Long  userId=authUtil.getCurrentUserId();
//        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId,userId)
//                .map(role->role.equals(ProjectRole.OWNER)|| role.equals(ProjectRole.VIEWER)
//                        ||role.equals(ProjectRole.EDITOR)).orElse(false);
    }



    //Who can Edit  this project
    public boolean canEditProject(Long projectId){

        return hasPermission(projectId, ProjectPermission.EDIT);

        //        Long  userId=authUtil.getCurrentUserId();
//        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId,userId)
//                .map(role->role.equals(ProjectRole.OWNER)
//                        ||role.equals(ProjectRole.EDITOR)).orElse(false);
    }

    //Who can delete  this project
    public boolean canDeleteProject(Long projectId) {

        return hasPermission(projectId, ProjectPermission.DELETE);
    }

    //Who can view members
    public boolean canViewMembers(Long projectId) {

        return hasPermission(projectId, ProjectPermission.VIEW_MEMBERS);
    }
    //Who can view members
    public boolean canManageMembers(Long projectId) {

        return hasPermission(projectId, ProjectPermission.MANAGE_MEMBERS);
    }
}
