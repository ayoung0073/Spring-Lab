package com.may.springbootmongodb.dto;

import com.may.springbootmongodb.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank
    private String name;

    private int age;

    public User saveUser(){
        return User.builder()
                .age(age)
                .name(name)
                .build();
    }
}
