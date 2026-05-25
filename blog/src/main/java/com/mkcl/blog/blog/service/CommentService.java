package com.mkcl.blog.blog.service;

import com.mkcl.blog.blog.entity.Comment;
import com.mkcl.blog.blog.entity.User;
import com.mkcl.blog.blog.repo.CommentRepository;
import com.mkcl.blog.blog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private UserRepository userRepo;

    public Comment addComment(Integer postId, Integer userId, String content) {
        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setContent(content);
        Comment savedComment = commentRepo.save(comment);

        // Update reputation: +5 for commenter
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setReputation(user.getReputation() + 5);
        userRepo.save(user);

        return savedComment;
    }
}
