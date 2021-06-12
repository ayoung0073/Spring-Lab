package com.may.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserProfileDto {
    private String email;
    private String nickname;
}
