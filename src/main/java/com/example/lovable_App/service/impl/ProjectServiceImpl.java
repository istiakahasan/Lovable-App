package com.example.lovable_App.service.impl;

import com.example.lovable_App.dto.project.ProjectRequest;
import com.example.lovable_App.dto.project.ProjectResponse;
import com.example.lovable_App.dto.project.ProjectSummaryResponse;
import com.example.lovable_App.entity.Project;
import com.example.lovable_App.entity.ProjectMember;
import com.example.lovable_App.entity.ProjectMemberId;
import com.example.lovable_App.entity.User;
import com.example.lovable_App.enums.ProjectRole;
import com.example.lovable_App.error.ResourceNotFoundException;
import com.example.lovable_App.mapper.ProjectMapper;
import com.example.lovable_App.repository.ProjectMemberRepository;
import com.example.lovable_App.repository.ProjectRepository;
import com.example.lovable_App.repository.UserRepository;
import com.example.lovable_App.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

     ProjectRepository projectRepository;
    UserRepository  userRepository;
    ProjectMapper projectMapper;
    ProjectMemberRepository projectMemberRepository;

    @Override
    public ProjectResponse createdProject(ProjectRequest request, Long userId) {
        User owner=userRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("User",userId)
        );

        Project project= Project.builder()
                .name(request.name())
                .isPublic(false)
                .build();//creating a project
        project =projectRepository.save(project);

        ProjectMemberId projectMemberId=new  ProjectMemberId(project.getId(), owner.getId());
        ProjectMember projectMember=ProjectMember.builder().id(projectMemberId)
                .projectRole(ProjectRole.OWNER)
                .user(owner).acceptedAt(Instant.now()).invitedAt(Instant.now())
                .project(project).build();
        projectMemberRepository.save(projectMember);

        return projectMapper.toProjectResponse(project);
    }



    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long useId) {

//       ***This whole code in shorter form:
//       return projectRepository.findAllAccessibleByUser(useId)
//       .stream().map(project->projectMapper.toProjectSummaryResponse(project))
//       .collect(Collectors.toList());
//        //#one way to shortcut the code :: map(projectMapped::toProjectSummaryResponse).collect(Collectors.toList());

        var projects=projectRepository.findAllAccessibleByUser(useId);
        return projectMapper.toProjectSummaryResponse(projects);
    }


    @Override
    public ProjectResponse getUserProjectById(Long userId,Long id) {
        Project project=projectRepository.findAccessibleProjectById(id, userId).orElseThrow();
        return  projectMapper.toProjectResponse(project);
    }



    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        Project project=getAccessibleProjectById(id,userId);


        project.setName(request.name());
        project=projectRepository.save(project);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id, Long userId) {
    Project project=getAccessibleProjectById(id,userId);


    project.setDeletedAt(Instant.now());
    projectRepository.save(project);
    }

    /// INTERNAL FUNCTION
    public Project getAccessibleProjectById(Long projectId,Long userId){
        return projectRepository.findAccessibleProjectById(projectId,userId).orElseThrow(()->new ResourceNotFoundException("project",projectId));
    }
}
