package com.may.jpapractice.domain.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Builder
@Entity(name = "member")
public class Member {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team")
    private Team team;          // 참조로 연관관계를 맺는다.

    private String username;

}
