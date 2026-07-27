package com.example.lovable_App.service.impl;

import com.example.lovable_App.dto.member.InviteMemberRequest;
import com.example.lovable_App.dto.member.MemberResponse;
import com.example.lovable_App.dto.member.UpdateMemberRoleRequest;
import com.example.lovable_App.entity.Project;
import com.example.lovable_App.entity.ProjectMember;
import com.example.lovable_App.entity.ProjectMemberId;
import com.example.lovable_App.entity.User;
import com.example.lovable_App.mapper.ProjectMemberMapper;
import com.example.lovable_App.repository.ProjectMemberRepository;
import com.example.lovable_App.repository.ProjectRepository;
import com.example.lovable_App.repository.UserRepository;
import com.example.lovable_App.security.AuthUtil;
import com.example.lovable_App.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    private final UserRepository userRepository;
    AuthUtil authUtil;

    @Override
    @PreAuthorize("@security.canViewMembers(#projectId)")
    public List<MemberResponse> getProjectMembers(Long projectId) {
        Long userId=authUtil.getCurrentUserId();
        Project project=getAccessibleProjectById(projectId,userId);
        return projectMemberRepository.findByProjectId(projectId)
                .stream().map(projectMemberMapper::toProjectMemberResponseFromMember)
                .collect(Collectors.toList());

    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request) {
        Long userId=authUtil.getCurrentUserId();
        Project project=getAccessibleProjectById(projectId,userId);


        ProjectMemberId projectMemberId=new ProjectMemberId(projectId,memberId);
        ProjectMember projectMember=projectMemberRepository.findById(projectMemberId).orElseThrow();

        projectMember.setProjectRole(request.role());
        projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request) {
        Long userId=authUtil.getCurrentUserId();
    Project project=getAccessibleProjectById(projectId,userId);


    User invitee=userRepository.findByUsername(request.username()).orElseThrow();
    if(Objects.equals(invitee.getId(), userId)){
        throw  new RuntimeException("Can not invite yourself");
    }
    ProjectMemberId projectMemberId=new ProjectMemberId(projectId,invitee.getId());
    if(projectMemberRepository.existsById(projectMemberId)){
        throw new RuntimeException("Can not invite once again");
    }

    ProjectMember member=ProjectMember.builder()
            .id(projectMemberId).project(project)
            .user(invitee).projectRole(request.role())
            .invitedAt(Instant.now()).build();
    projectMemberRepository.save(member);

      return projectMemberMapper.toProjectMemberResponseFromMember(member);
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public void removeProjectMember(Long projectId, Long memberId) {
        Long userId=authUtil.getCurrentUserId();
        Project project=getAccessibleProjectById(projectId,userId);//we are watching if project exists or not.


        ProjectMemberId projectMemberId=new ProjectMemberId(projectId,memberId);
        if(!projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Member not found in project");
        }

        projectMemberRepository.deleteById(projectMemberId);


    }





    //INTERNAL FUNCTION
    public Project getAccessibleProjectById(Long projectId, Long userId){
        return projectRepository.findAccessibleProjectById(projectId,userId).orElseThrow();
    }
}
