package com.josegrd.web_self_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "blog_content")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogContent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "content")
    private String content;

    @Column(name = "content_order", nullable = false)
    private Integer contentOrder;
}
