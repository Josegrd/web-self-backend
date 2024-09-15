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
@Table(name = "m_blogs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "heading_image_url")
    private String headingImageUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<BlogContent> contents;
}
