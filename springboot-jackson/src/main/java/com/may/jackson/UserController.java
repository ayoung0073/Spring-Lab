package com.may.jackson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    @PostMapping("")
    public Object saveUser(@RequestParam("type") String type, @RequestBody SignUpDto signUpDto) {
        switch (type) {
            case "json_ignore":
                User_JsonIgnore user_jsonIgnore = User_JsonIgnore.builder()
                        .email(signUpDto.getEmail())
                        .intro(signUpDto.getIntro())
                        .age(signUpDto.getAge())
                        .name(signUpDto.getName())
                        .password(signUpDto.getPassword())
                        .build();
                log.info(user_jsonIgnore.toString());
                return user_jsonIgnore;

            case "json_ignore_properties":
                User_JsonIgnoreProperties user_jsonIgnoreProperties = User_JsonIgnoreProperties.builder()
                        .email(signUpDto.getEmail())
                        .intro(signUpDto.getIntro())
                        .name(signUpDto.getName())
                        .age(signUpDto.getAge())
                        .password(signUpDto.getPassword())
                        .build();
                log.info(user_jsonIgnoreProperties.toString());
                return user_jsonIgnoreProperties;

            default:
                return null;
        }
    }
}
