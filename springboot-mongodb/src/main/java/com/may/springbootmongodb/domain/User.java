package com.may.springbootmongodb.domain;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User {

    @Id
    private String id;

    @Indexed(unique=true)
    private String name;

    private int age;

    @Builder
    public User(String name, int age){
        this.name = name;
        this.age = age;
    }


}
