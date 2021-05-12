package com.may.tddpractice.service;

import com.may.tddpractice.domain.UserRepository;
import com.may.tddpractice.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
        // 이 코드 없으면 NPE
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("mokito repository 테스트")
    void mokitoTest() {
        // given
        User user = User.builder()
                .age(22)
                .email("ayong0310@naver.com")
                .name("문아영")
                .build();

        List<User> userList = new ArrayList<>();
        userList.add(user);

        given(userRepository.findAll()).willReturn(userList);
        // given 을 이용해 userRepository.findAll() 을 조회할 경우 리턴할 값을 지정


        // when
        List<User> findUserList = userRepository.findAll();

        // then
        assertThat(findUserList).isEqualTo(userList);
    }

//    @Test
//    @DisplayName("회원 추가 service 테스트")
//    void saveRepoTest(){
//        // given
//        User user = User.builder()
//                .name("문마리")
//                .age(11)
//                .build();
//
//        // when
//        userService.saveUser(user);
//
//        // then
//        verify(userRepository).save(user);
//        // UserRepository save 메서드 호출했는지 확인
//    }

    @BeforeEach
        // BeforeAll : static 메서드로 선언해줘야 함
    void setup() {
        User user1 = User.builder()
                .age(7)
                .name("문마음")
                .build();


        User user2 = User.builder()
                .age(1)
                .name("문마음")
                .build();

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        given(userRepository.findAll()).willReturn(userList);
    }


    @Test
    @DisplayName("회원 정보 조회 repository 테스트")
    void findAllRepoTest() {
        // given

        // when
        List<User> userList = userRepository.findAll();

        // then
        assertThat(userList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("회원 정보 조회 service 테스트")
    void findAllServiceTest() {
        // given

        // when
        List<User> userList = userService.findAll();

        // then
        assertThat(userList.size()).isEqualTo(2);
    }


}