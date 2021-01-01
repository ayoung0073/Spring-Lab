package com.may.blog.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity // User 클래스가 MySQL 에 테이블이 자동으로 생성이 됨
public class User {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 넘버링 전략 따라감
    private Long id; // sequence, AI

    @Column(nullable = false, length = 30)
    private String username; // 아이디

    @Column(nullable = false, length = 100) // 해쉬(비밀번호 암호화 할거임)
    private String password; // 비밀번호

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'") // "' '"
    private String role; // Enum 쓰는 게 좋음 // admin, user, manager

    @CreatedDate
    private LocalDateTime createdAt;
}
