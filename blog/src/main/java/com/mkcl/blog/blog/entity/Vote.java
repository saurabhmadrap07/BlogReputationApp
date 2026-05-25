package com.mkcl.blog.blog.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Vote")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TargetType targetType;

    @Column(nullable = false)
    private Integer targetId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Boolean isUpvote;

    public enum TargetType {
        post,
        comment
    }
}
