package com.josegrd.web_self_api.service;

import com.josegrd.web_self_api.dto.BlogDTO;
import com.josegrd.web_self_api.entity.Blog;

import java.util.List;
import java.util.UUID;

public interface BlogService {
    BlogDTO createBlog(BlogDTO blogDTO);
    BlogDTO updateBlog(UUID id, BlogDTO blogDTO);
    void deleteBlog(UUID id);
    List<BlogDTO> getBlogs();
    BlogDTO getBlog(UUID id);
    void deleteAllBlogs();
}
