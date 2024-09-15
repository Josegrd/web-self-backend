package com.josegrd.web_self_api.repository;

import com.josegrd.web_self_api.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
