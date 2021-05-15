package com.may.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ToString
@Builder
@Getter
@JsonIgnoreProperties({"id", "password"})
public class User_JsonIgnoreProperties {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String email;
    private String password;
    private int age;
    private String intro;
}
