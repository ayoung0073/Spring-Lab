package com.may.blog.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne // 하나의 게시글에 여러개 답변 -> ManyToOne
    @JoinColumn(name="boardId")
    private Board board;

    @ManyToOne // 하나의 유저는 여러개의 답변할 수 있음 , 하나의 답변을 여러명이 쓸수는 없음
    @JoinColumn(name="userId")
    private User user;

    //     alter table Reply
    //       add constraint FKqnspgy412rv4dfcmv69hsf4px
    //       foreign key (userId)
    //       references User (id)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
