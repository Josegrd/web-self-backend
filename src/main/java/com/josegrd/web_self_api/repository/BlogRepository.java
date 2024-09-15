package com.josegrd.web_self_api.repository;

import com.josegrd.web_self_api.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlogRepository extends JpaRepository<Blog, UUID> {
}
