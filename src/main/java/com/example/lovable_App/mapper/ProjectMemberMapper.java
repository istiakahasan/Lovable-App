package com.example.lovable_App.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.example.lovable_App.dto.member.MemberResponse;
import com.example.lovable_App.entity.ProjectMember;
import com.example.lovable_App.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface ProjectMemberMapper {
    @Mapping(target = "userId",source = "id")
    @Mapping(target = "projectRole",constant = "OWNER")
    MemberResponse toProjectMemberResponseFromOwner(User owner);

    @Mapping(target = "userId",source = "user.id")
    @Mapping(target = "username",source = "user.username")
    @Mapping(target = "name",source = "user.name")
    MemberResponse toProjectMemberResponseFromMember(ProjectMember projectMember);
}
