package com.josegrd.web_self_api.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {

    private UUID id;
    private String title;
    private String author;
    private String headingImageUrl;
    private String imageUrl;
    private List<BlogContentDTO> contents;
    private LocalDateTime createdDate;
}
