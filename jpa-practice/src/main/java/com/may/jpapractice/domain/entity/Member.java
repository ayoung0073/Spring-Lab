package com.may.jpapractice.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter @Setter
@Builder
@Entity(name = "member")
// @DynamicUpdate // 수정된 데이터만 UPDATE SQL 생성
public class Member {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;          // 참조로 연관관계를 맺는다.

    private String username;

    private String email;

}
