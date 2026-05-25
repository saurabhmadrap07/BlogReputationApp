package com.mkcl.blog.blog.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer postId;

    @Column(nullable = false)
    private Integer userId;

    @Column(columnDefinition = "TEXT")
    private String content;
}
