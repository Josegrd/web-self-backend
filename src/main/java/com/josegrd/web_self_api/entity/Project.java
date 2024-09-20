package com.josegrd.web_self_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "m_project")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "url_project")
    private String urlProject;

    @Column(name = "start_project")
    private String startProject;

    @Column(name = "end_project")
    private String endProject;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ElementCollection
    @CollectionTable(name = "m_project_stack", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "stack")
    private List<String> stack;
}
