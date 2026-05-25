package com.mkcl.blog.blog.service;

import com.mkcl.blog.blog.entity.BlogPost;
import com.mkcl.blog.blog.entity.Comment;
import com.mkcl.blog.blog.repo.BlogPostRepository;
import com.mkcl.blog.blog.repo.CommentRepository;
import com.mkcl.blog.blog.repo.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepo;

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private VoteRepository voteRepo;

    // Fetch all blog posts
    public List<BlogPost> getAllPosts() {
        return blogPostRepo.findAll();
    }

    // Fetch comments for a post
    public List<Comment> getCommentsForPost(Integer postId) {
        return commentRepo.findAll().stream()
                .filter(c -> c.getPostId().equals(postId))
                .toList();
    }

    // Count upvotes for a post
    public long countUpvotesForPost(Integer postId) {
        return voteRepo.countByTargetTypeAndTargetIdAndIsUpvote("post", postId, true);
    }

    // Count downvotes for a post
    public long countDownvotesForPost(Integer postId) {
        return voteRepo.countByTargetTypeAndTargetIdAndIsUpvote("post", postId, false);
    }

    // Similarly, methods can be added for comments’ votes if required
}
