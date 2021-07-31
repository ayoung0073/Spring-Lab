package com.may.jpapractice.domain;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    @Column(nullable = false)
    private String content;

    @Builder
    public Comment(Board board, Member writer, String content) {
        this.board = board;
        this.writer = writer;
        this.content = content;
    }

}
