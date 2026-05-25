package com.mkcl.blog.blog.repo;

import com.mkcl.blog.blog.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    long countByTargetTypeAndTargetIdAndIsUpvote(String targetType, Integer targetId, boolean isUpvote);


}
