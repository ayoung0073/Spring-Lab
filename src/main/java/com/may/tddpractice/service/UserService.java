package com.may.tddpractice.service;

import com.may.tddpractice.domain.UserRepository;
import com.may.tddpractice.domain.dto.UserDto;
import com.may.tddpractice.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user){


        userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }


}
