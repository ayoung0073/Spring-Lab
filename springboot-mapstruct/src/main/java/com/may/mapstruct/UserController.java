package com.may.mapstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("")
    public ResponseEntity<?> signup(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(userService.signUp(userMapper.signUpDtoToEntity(signUpDto)));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userMapper.toProfileDto(userService.getProfile(userId)));
    }
}
