package com.example.lovable_App.mapper;

import com.example.lovable_App.dto.project.ProjectResponse;
import com.example.lovable_App.dto.project.ProjectSummaryResponse;
import com.example.lovable_App.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);
    @Mapping(source = "name",target = "projectName")
    ProjectSummaryResponse toProjectSummaryResponse(Project project);

    List<ProjectSummaryResponse> toProjectSummaryResponse(List<Project> projects);

}
