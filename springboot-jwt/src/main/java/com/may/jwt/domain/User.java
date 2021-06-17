package com.may.jwt.domain;

import lombok.*;

@Getter @Setter
@Builder
public class User {
    private Long id;
    private String email;
    private String password;
}
