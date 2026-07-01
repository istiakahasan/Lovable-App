package com.example.lovable_App.controller;

import com.example.lovable_App.dto.project.ProjectRequest;
import com.example.lovable_App.dto.project.ProjectResponse;
import com.example.lovable_App.dto.project.ProjectSummaryResponse;
import com.example.lovable_App.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/projects")
@RequiredArgsConstructor
@RestController
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectSummaryResponse>> getMyProjects(){
        Long useId=1L;
        return ResponseEntity.ok(projectService.getUserProjects(useId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id){
        Long userId=1L;
    return ResponseEntity.ok(projectService.getUserProjectById(userId,id));
    }
    @PostMapping
    public ResponseEntity<ProjectResponse> createdProject(@RequestBody @Valid ProjectRequest request){
        long useId=1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createdProject(request,useId));
    }
    @PatchMapping("/{id}")
    public  ResponseEntity<ProjectResponse>  updateProject(@RequestBody @Valid ProjectRequest request,@PathVariable Long id){
        Long userId=1L;
        return ResponseEntity.ok(projectService.updateProject(id,request,userId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectResponse> deleteProject(@PathVariable Long id){
        Long userId=1L;
         projectService.softDelete(id,userId);
         return ResponseEntity.noContent().build();
    }

}
