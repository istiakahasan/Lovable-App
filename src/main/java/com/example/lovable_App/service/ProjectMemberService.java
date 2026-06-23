package com.example.lovable_App.service;

import com.example.lovable_App.dto.member.InviteMemberRequest;
import com.example.lovable_App.dto.member.MemberResponse;
import com.example.lovable_App.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {

     List<MemberResponse> getProjectMembers(Long projectId, Long userId);

     MemberResponse updateMemberRole(Long projectId, Long memberId,
                                           UpdateMemberRoleRequest request, Long userId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);

    void removeProjectMember(Long projectId, Long memberId, Long userId);
}
