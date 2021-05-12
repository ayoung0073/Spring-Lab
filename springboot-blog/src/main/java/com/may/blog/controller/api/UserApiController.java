package com.may.blog.controller.api;

import com.may.blog.config.auth.PrincipalDetail;
import com.may.blog.dto.ResponseDto;
import com.may.blog.model.RoleType;
import com.may.blog.model.User;
import com.may.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) throws Exception { // username, password, email

        userService.join(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해 리턴(Jackson)
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) throws Exception { // username, password, email

        userService.updateUser(user);


        // 세션 등록 (자동으로 해줄거임) // service에서 하면안됨, 영속화되어있어 서비스가 끝나고 컨트롤러에서 진행해야, 바뀐 패스워드로 진행 가능
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

//        Authentication authentication =
//                new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities()); // 1번째 파라미터만 알면 됨
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        securityContext.setAuthentication(authentication);
//        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

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
