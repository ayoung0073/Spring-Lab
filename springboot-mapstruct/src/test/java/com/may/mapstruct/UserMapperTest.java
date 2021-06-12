package com.may.mapstruct;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserMapperTest {

    @Test
    void SignUpDto_TO_UserEntity_테스트() {
        // given
        final SignUpDto signUpDto = SignUpDto.builder()
                .email("email@email.com")
                .nickname("애용")
                .password("password")
                .build();
        // when
        final UserEntity user = UserMapper.INSTANCE.signUpDtoToEntity(signUpDto);
        //then
        assertNotNull(user);
        assertThat(user.getEmail(), is(signUpDto.getEmail()));
        assertThat(user.getNickname(), is(signUpDto.getNickname()));
        // user.getId() : null
    }
}
