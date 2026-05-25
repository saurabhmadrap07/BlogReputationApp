package com.mkcl.blog.blog.controller;

import com.mkcl.blog.blog.entity.BlogPost;
import com.mkcl.blog.blog.entity.Comment;
import com.mkcl.blog.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    // Fetch all blog posts
    @GetMapping
    public List<BlogPost> getAllPosts() {
        return blogPostService.getAllPosts();
    }

    // Get comments for a post
    @GetMapping("/{postId}/comments")
    public List<Comment> getCommentsForPost(@PathVariable Integer postId) {
        return blogPostService.getCommentsForPost(postId);
    }
}
