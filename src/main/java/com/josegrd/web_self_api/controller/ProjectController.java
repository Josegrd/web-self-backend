package com.josegrd.web_self_api.controller;

import com.josegrd.web_self_api.dto.ProjectDTO;
import com.josegrd.web_self_api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.createProject(projectDTO);
        return ResponseEntity.ok(createdProject);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getProjects() {
        List<ProjectDTO> projectDTOS = projectService.getProjects();
        return ResponseEntity.ok(projectDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable UUID id) {
        ProjectDTO projectDTO = projectService.getProject(id);
        return ResponseEntity.ok(projectDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable UUID id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Projects deleted successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllProjects() {
        projectService.deleteAllProjects();
        return ResponseEntity.ok("Projects deleted successfully");
    }
}
