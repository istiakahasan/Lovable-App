package com.example.lovable_App.service;

import com.example.lovable_App.dto.member.InviteMemberRequest;
import com.example.lovable_App.dto.member.MemberResponse;
import com.example.lovable_App.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {

     List<MemberResponse> getProjectMembers(Long projectId);

     MemberResponse updateMemberRole(Long projectId, Long memberId,
                                           UpdateMemberRoleRequest request);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request);

    void removeProjectMember(Long projectId, Long memberId);
}
