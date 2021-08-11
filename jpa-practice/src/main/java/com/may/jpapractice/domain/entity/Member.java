package com.may.jpapractice.domain.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Entity
class Member {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member")
    private Team team;          // 참조로 연관관계를 맺는다.

    private String username;

}
