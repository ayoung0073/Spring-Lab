package com.may.jwt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.may.jwt.TokenDto;
import com.may.jwt.domain.User;
import com.may.jwt.repository.UserRepositoryImpl;
import com.may.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();

    private final JwtService jwtService;

    // 회원가입
    public TokenDto signUp(User user) throws JsonProcessingException {
        userRepository.save(user);
        return jwtService.createTokenResponse(user.getId());
    }

    // id로 회원 조회
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // accessToken 인증
    public Long authAccessToken(String accessToken) throws JsonProcessingException {
        return jwtService.getPayload(accessToken);
    }

}
