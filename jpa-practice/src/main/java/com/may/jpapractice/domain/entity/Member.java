package com.may.jpapractice.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Builder
@Entity(name = "member")
// @DynamicUpdate // 수정된 데이터만 UPDATE SQL 생성
public class Member extends BaseEntity{

    @Id
    @Column(name = "member_id")
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;          // 참조로 연관관계를 맺는다.

    private String username;

    private String email;

}
