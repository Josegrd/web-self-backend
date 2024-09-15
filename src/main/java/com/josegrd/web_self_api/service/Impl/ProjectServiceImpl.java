package com.josegrd.web_self_api.service.Impl;

import com.josegrd.web_self_api.dto.ProjectDTO;
import com.josegrd.web_self_api.entity.Project;
import com.josegrd.web_self_api.repository.ProjectRepository;
import com.josegrd.web_self_api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = Project.builder()
                .title(projectDTO.getTitle())
                .description(projectDTO.getDescription())
                .imageUrl(projectDTO.getImageUrl())
                .urlProject(projectDTO.getUrlProject())
                .startProject(projectDTO.getStartProject())
                .endProject(projectDTO.getEndProject())
                .stack(projectDTO.getStack())
                .build();
        projectRepository.save(project);
        return mapToDTO(project);
    }

    @Override
    public String deleteProject(UUID id) {
        Project project = getProjectById(id);
        projectRepository.delete(project);
        return "Project deleted";
    }

    @Override
    public List<ProjectDTO> getProjects() {
        List<Project> projects = projectRepository.findAll();

        return projects.stream().map(this::mapToDTO).toList();
    }

    @Override
    public ProjectDTO getProject(UUID id) {
        Project project = getProjectById(id);
        return mapToDTO(project);
    }

    @Override
    public String deleteAllProjects() {
        projectRepository.deleteAll();
        return "All projects deleted";
    }

    private Project getProjectById(UUID id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
    }

    private ProjectDTO mapToDTO(Project project) {
        ProjectDTO response = ProjectDTO.builder()
                .title(project.getTitle())
                .description(project.getDescription())
                .imageUrl(project.getImageUrl())
                .urlProject(project.getUrlProject())
                .startProject(project.getStartProject())
                .endProject(project.getEndProject())
                .stack(project.getStack())
                .build();

        return response;
    }
}
