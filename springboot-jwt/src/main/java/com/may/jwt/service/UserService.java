package com.may.jwt.service;

import com.may.jwt.domain.User;
import com.may.jwt.repository.UserMemoryRepository;
import com.may.jwt.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository = new UserMemoryRepository();

    // 회원가입
    public Long signUp(User user) {
        userRepository.save(user);
        return user.getId(); // 후에 토큰 정보 리턴할 것
    }

    // id로 회원 조회
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
