package com.may.mapstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("")
    public ResponseEntity<?> signup(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(userService.signUp(userMapper.signUpDtoToEntity(signUpDto)));
    }
}
