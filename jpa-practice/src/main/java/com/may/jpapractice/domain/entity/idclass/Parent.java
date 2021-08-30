package com.may.jpapractice.domain.entity.idclass;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "parent")
public class Parent {

    @Id @Column(name = "parent_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
