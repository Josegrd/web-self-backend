package com.josegrd.web_self_api.repository;

import com.josegrd.web_self_api.entity.Blog;
import com.josegrd.web_self_api.entity.BlogContent;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlogContentRepository extends JpaRepository<BlogContent, UUID> {
    @Transactional
    void deleteAllByBlog(Blog blog);
}
