package com.may.jpapractice.domain.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
class Team {

    @Id
    Long id;

    String name;
}