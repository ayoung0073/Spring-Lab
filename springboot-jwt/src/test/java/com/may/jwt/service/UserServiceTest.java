package com.may.jwt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.may.jwt.TokenDto;
import com.may.jwt.domain.User;
import com.may.jwt.repository.UserRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @AfterEach
    public void afterEach() { // 테스트간 종속 제거
        userRepository.clearStore();
    }

    @Test
    public void 회원가입_테스트() throws JsonProcessingException {
        // given
        User user = User.builder()
                .email("test@test.com")
                .password("test")
                .build();
        userService.signUp(user);
        assertThat(user.getId(), is(1L));
        assertThat(userService.findById(1L).get(), is(user));
    }

    @Test
    public void 액세스토큰_인증_테스트() throws JsonProcessingException {
        // given
        User user = User.builder()
                .email("test@test.com")
                .password("test")
                .build();
        TokenDto tokenDto = userService.signUp(user);
        assertThat(userService.authAccessToken(tokenDto.getAccessToken()), is(user.getId()));
    }
}