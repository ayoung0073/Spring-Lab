package com.may.blog.controller.api;

import com.may.blog.dto.ResponseDto;
import com.may.blog.model.RoleType;
import com.may.blog.model.User;
import com.may.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;


    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) throws Exception { // username, password, email
        System.out.println("save 호출됨");

        userService.join(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해 리턴(Jackson)
    }

//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) throws Exception { // username, password, email
//        System.out.println("login 호출됨");
//        User principal = userService.login(user); // principal : 접근주체
//
//        if(principal != null){
//            session.setAttribute("principal", principal);
//        }
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해 리턴(Jackson)
//    }
}
