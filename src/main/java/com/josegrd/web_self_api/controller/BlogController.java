package com.josegrd.web_self_api.controller;


import com.josegrd.web_self_api.dto.BlogDTO;
import com.josegrd.web_self_api.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;


    @PostMapping
    public ResponseEntity<BlogDTO> createBlog(@RequestBody BlogDTO blogDTO) {
        BlogDTO createdBlog = blogService.createBlog(blogDTO);
        return ResponseEntity.ok(createdBlog);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDTO> getBlog(@PathVariable UUID id) {
        BlogDTO blogDTO = blogService.getBlog(id);
        return ResponseEntity.ok(blogDTO);
    }

    @GetMapping
    public ResponseEntity<List<BlogDTO>> getBlogs() {
        List<BlogDTO> blogDTOS = blogService.getBlogs();
        return ResponseEntity.ok(blogDTOS);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BlogDTO> updateBlog(@PathVariable UUID id, @RequestBody BlogDTO blogDTO) {
        BlogDTO updatedBlog = blogService.updateBlog(id,blogDTO);
        return ResponseEntity.ok(updatedBlog);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable UUID id) {
        blogService.deleteBlog(id);
        return ResponseEntity.ok("Blog deleted successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBlogs() {
        blogService.deleteAllBlogs();
        return ResponseEntity.ok("Blog deleted successfully");
    }
}
