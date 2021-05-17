package com.may.tddpractice.service;

import com.may.tddpractice.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setup() {
        User user = User.builder()
                .age(7)
                .name("문마음")
                .build();

        userService.saveUser(user);
    }

    @Test
    public void 회원가입_성공_테스트() {
        // given
        User user = User.builder()
                .age(22)
                .email("ayong0310@naver.com")
                .name("문아영")
                .build();

        // when
        userService.saveUser(user);

        // given
        Optional<User> findUser = userService.findByName(user.getName());
        assertThat(findUser.get().getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void 회원중복_예외_테스트() {
        // given
        User user = User.builder()
                .age(7)
                .name("문마음")
                .build();

        // when
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> userService.saveUser(user)); // 예외가 발생해야 한다.

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}
