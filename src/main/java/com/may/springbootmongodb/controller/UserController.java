package com.may.springbootmongodb.controller;

import com.may.springbootmongodb.domain.User;
import com.may.springbootmongodb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUserList(){
        return userRepository.findAll();
    }


}
