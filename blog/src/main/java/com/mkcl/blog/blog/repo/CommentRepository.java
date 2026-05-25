package com.mkcl.blog.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkcl.blog.blog.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    

}
