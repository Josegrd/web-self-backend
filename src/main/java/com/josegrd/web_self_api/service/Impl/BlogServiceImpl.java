package com.josegrd.web_self_api.service.Impl;

import com.josegrd.web_self_api.dto.BlogContentDTO;
import com.josegrd.web_self_api.dto.BlogDTO;
import com.josegrd.web_self_api.entity.Blog;
import com.josegrd.web_self_api.entity.BlogContent;
import com.josegrd.web_self_api.repository.BlogContentRepository;
import com.josegrd.web_self_api.repository.BlogRepository;
import com.josegrd.web_self_api.service.BlogService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final BlogContentRepository blogContentRepository;

    @Override
    public BlogDTO createBlog(BlogDTO blogDTO) {
        Blog blog1 = Blog.builder()
                .title(blogDTO.getTitle())
                .author(blogDTO.getAuthor())
                .headingImageUrl(blogDTO.getHeadingImageUrl())
                .imageUrl(blogDTO.getImageUrl())
                .build();

        blog1 = blogRepository.save(blog1);

        int order = 1;

        for (BlogContentDTO blogContentDTO : blogDTO.getContents()) {
            BlogContent content = BlogContent.builder()
                    .blog(blog1)
                    .contentType(blogContentDTO.getContentType())
                    .content(blogContentDTO.getContent())
                    .contentOrder(order++)
                    .build();
            blogContentRepository.save(content);
        }
        return blogDTO;
    }


    @Override
    public BlogDTO updateBlog(UUID id, BlogDTO blogDTO) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Blog not found"));

        // Update basic blog fields
        blog.setTitle(blogDTO.getTitle());
        blog.setAuthor(blogDTO.getAuthor());
        blog.setHeadingImageUrl(blogDTO.getHeadingImageUrl());
        blog.setImageUrl(blogDTO.getImageUrl());

        // Delete old contents
        blogContentRepository.deleteAllByBlog(blog);

        // Add new contents with auto-increment order
        int order = 1;
        for (BlogContentDTO contentDTO : blogDTO.getContents()) {
            BlogContent content = BlogContent.builder()
                    .blog(blog)
                    .contentType(contentDTO.getContentType())
                    .content(contentDTO.getContent())
                    .contentOrder(order++)
                    .build();

            blogContentRepository.save(content);
        }

        blogRepository.save(blog);
        return mapToDTO(blog);
    }

    @Transactional
    @Override
    public void deleteBlog(UUID id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Blog not found"));

        blogRepository.delete(blog);
    }

    public void deleteAllBlogs() {
        blogRepository.deleteAll();
    }

    @Override
    public List<BlogDTO> getBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public BlogDTO getBlog(UUID id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Blog not found"));

        return mapToDTO(blog);
    }

    private BlogDTO mapToDTO(Blog blog) {
        List<BlogContentDTO> contents = blog.getContents().stream().map(content -> BlogContentDTO.builder()
                .contentType(content.getContentType())
                .content(content.getContent())
                .build())
                .collect(Collectors.toList());

        return BlogDTO.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .author(blog.getAuthor())
                .headingImageUrl(blog.getHeadingImageUrl())
                .imageUrl(blog.getImageUrl())
                .contents(contents)
                .build();
    }

    private Blog findByIdOrThrowNotFound(UUID id){
        return blogRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found"));
    }
}
