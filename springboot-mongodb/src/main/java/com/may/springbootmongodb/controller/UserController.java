package com.may.springbootmongodb.controller;

import com.may.springbootmongodb.domain.User;
import com.may.springbootmongodb.dto.UserDto;
import com.may.springbootmongodb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository; // 1번째 방법
    
    private final MongoTemplate mongoTemplate; // 2번째 방법

    @GetMapping("repo/users")
    public ResponseEntity<?> getUserListByRepo(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("template/users")
    public ResponseEntity<?> getUserListByTemplate(){
        return new ResponseEntity<>(mongoTemplate.findAll(User.class), HttpStatus.OK);
    }

    @PostMapping("repo/user")
    public ResponseEntity<?> saveUserByRepo(@Valid @RequestBody UserDto userDto){
       User user = userDto.saveUser();
        System.out.println(userDto.toString());
       userRepository.save(user);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("template/user")
    public ResponseEntity<?> saveUserByTemplate(@Valid @RequestBody UserDto userDto){
        User user = userDto.saveUser();
        System.out.println(userDto.toString());
        mongoTemplate.insert(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("repo/user")
    public ResponseEntity<?> deleteUserByRepo(@RequestBody UserDto userDto){
        System.out.println(userDto.getName());
        Long ret = userRepository.deleteByName(userDto.getName()); // 1이면 delete, 0이면 없는 User

        if (ret == 1)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
