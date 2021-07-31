package com.may.jpapractice.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "writer") // 회원이 작성한 게시판 리스트
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "writer")// 회원이 작성한 댓글 리스트
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Member(String name) {
        this.name = name;
    }

}
