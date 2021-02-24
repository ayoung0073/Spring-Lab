package com.may.mybatispractice.controller;

import com.may.mybatispractice.dto.UserDto;
import com.may.mybatispractice.mapper.UserMapper;
import com.may.mybatispractice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }


}
