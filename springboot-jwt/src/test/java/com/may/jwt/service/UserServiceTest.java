package com.may.jwt.service;

import com.may.jwt.domain.User;
import com.may.jwt.repository.UserMemoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMemoryRepository userRepository;

    @AfterEach
    public void afterEach() {
        userRepository.clearStore();
    }

    @Test
    public void 회원가입_테스트() {
        // given
        User user = User.builder()
                .email("test@test.com")
                .password("test")
                .build();
        userService.signUp(user);
        assertThat(user.getId(), is(1L));
        assertThat(userService.findById(1L).get(), is(user));
    }
}