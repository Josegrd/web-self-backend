package com.josegrd.web_self_api.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDTO {
    private String title;
    private String description;
    private String imageUrl;
    private String urlProject;
    private String startProject;
    private String endProject;
    private List<String> stack;
}
