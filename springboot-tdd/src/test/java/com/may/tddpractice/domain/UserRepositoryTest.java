package com.may.tddpractice.domain;

import com.may.tddpractice.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void 유저_저장_테스트() {
        // given
        User user = User.builder()
                .name("테스트")
                .email("test@gmail.com")
                .age(22)
                .build();
        // when
        userRepository.save(user);

        // then
        assertThat(userRepository.findByName(user.getName()).isPresent()).isEqualTo(true);
    }
}
