package com.may.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ToString
@Builder
@Getter
public class User_JsonIgnore {
    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;
    private String name;
    private String email;
    private int age;
    @JsonIgnore
    private String password;
    private String intro;
}
