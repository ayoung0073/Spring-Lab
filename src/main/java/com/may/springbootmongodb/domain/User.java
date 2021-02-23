package com.may.springbootmongodb.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;

@Setter
@NoArgsConstructor
@Getter
@Document(collection = "user")
public class User {

    @Id
    @Column(name = "_id")
    private String id;

    private String name;

    private int age;
}
