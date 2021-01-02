package com.may.blog.service;

import com.may.blog.model.RoleType;
import com.may.blog.model.User;
import com.may.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
// 스프링이 컴포넌트 스캔을 통해 Bean에 등록 해줌, IoC
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public int join(User user) throws Exception{
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        user.setRole(RoleType.USER);

        String rawPassword = user.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encPassword);

        userRepository.save(user);

        return 1;
    }

//    @Transactional(readOnly = true) // select 할 때 트랜잭션 시작, 서비스 종료 시에 트랜잭션 종료(정합성)
//    public User login(User user) throws Exception{
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
}
