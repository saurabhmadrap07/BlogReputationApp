package com.mkcl.blog.blog.controller;

import com.mkcl.blog.blog.entity.Comment;
import com.mkcl.blog.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Add comment to a post
    @PostMapping("/{postId}/add")
    public Comment addComment(@PathVariable Integer postId,
                              @RequestParam Integer userId,
                              @RequestBody String content) {
        return commentService.addComment(postId, userId, content);
    }
}
