package com.may.mapstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Long signUp(UserEntity userEntity) {
        UserEntity user = userRepository.save(userEntity);
        return user.getId();
    }

    public UserEntity getProfile(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

}
