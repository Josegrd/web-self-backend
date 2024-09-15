package com.josegrd.web_self_api.service;

import com.josegrd.web_self_api.dto.ProjectDTO;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    ProjectDTO createProject(ProjectDTO projectDTO);
    String deleteProject(UUID id);
    List<ProjectDTO> getProjects();
    ProjectDTO getProject(UUID id);
    String deleteAllProjects();
}
