package com.example.lovable_App.service;

import com.example.lovable_App.dto.project.ProjectRequest;
import com.example.lovable_App.dto.project.ProjectResponse;
import com.example.lovable_App.dto.project.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {


    ProjectResponse createdProject(ProjectRequest request, long useId) ;

    List<ProjectSummaryResponse> getUserProjects(Long useId);

    //ProjectResponse getUserProjectById(Long userId);

    ProjectResponse getUserProjectById(Long userId, Long id);

    ProjectResponse updateProject(Long id, ProjectRequest request, Long userId);

    void softDelete(Long id, Long userId);
}
