package com.may.tddpractice.controller;

import com.may.tddpractice.domain.dto.UserDto;
import com.may.tddpractice.domain.entity.User;
import com.may.tddpractice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto){
        User user = User.builder()
                .age(userDto.getAge())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
