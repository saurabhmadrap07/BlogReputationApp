package com.mkcl.blog.blog.service;

import com.mkcl.blog.blog.entity.User;
import com.mkcl.blog.blog.entity.Vote;
import com.mkcl.blog.blog.entity.Comment;
import com.mkcl.blog.blog.entity.BlogPost;
import com.mkcl.blog.blog.repo.BlogPostRepository;
import com.mkcl.blog.blog.repo.CommentRepository;
import com.mkcl.blog.blog.repo.UserRepository;

import com.mkcl.blog.blog.repo.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BlogPostRepository blogPostRepo;

    @Autowired
    private CommentRepository commentRepo;

    public Vote castVote(String targetType, Integer targetId, Integer userId, boolean isUpvote) {
        // Save vote
        Vote vote = new Vote();
        vote.setTargetType(Vote.TargetType.valueOf("post".toLowerCase()));
        vote.setTargetId(targetId);
        vote.setUserId(userId);
        vote.setIsUpvote(isUpvote);
        Vote savedVote = voteRepo.save(vote);

        // Update reputation of target's author
        Integer authorId = getAuthorIdByTarget(targetType, targetId);

        if (authorId == null) {
            throw new RuntimeException("Target author not found");
        }

        User author = userRepo.findById(authorId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        int reputationChange = 0;
        if (isUpvote) {
            reputationChange = 2;  // +2 per upvote
        } else {
            reputationChange = -3; // -3 per downvote
        }

        author.setReputation(author.getReputation() + reputationChange);
        userRepo.save(author);

        // If vote is on a post and is a downvote, check if penalty applies
        if ("post".equals(targetType) && !isUpvote) {
            applyDownvotePenaltyIfNeeded(targetId, author);
        }

        return savedVote;
    }

    private Integer getAuthorIdByTarget(String targetType, Integer targetId) {
        if ("post".equals(targetType)) {
            return blogPostRepo.findById(targetId)
                    .map(BlogPost::getUserId)
                    .orElse(null);
        } else if ("comment".equals(targetType)) {
            return commentRepo.findById(targetId)
                    .map(Comment::getUserId)
                    .orElse(null);
        }
        return null;
    }

    private void applyDownvotePenaltyIfNeeded(Integer postId, User author) {
        long downvoteCount = voteRepo.countByTargetTypeAndTargetIdAndIsUpvote("post", postId, false);
        if (downvoteCount > 5) {
            // Deduct 10 reputation once per condition met
            // To avoid repeated penalty, decide on strategy (not implemented here)
            author.setReputation(author.getReputation() - 10);
            userRepo.save(author);
        }
    }
}
