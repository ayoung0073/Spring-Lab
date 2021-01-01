package com.may.blog.test;

import com.may.blog.model.User;
import com.may.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DummyControllerTest {

    private final UserRepository userRepository;
    // 스프링이 componentScan할 때, UserRepository를 메모리에 올려두기 때문에, 사용하기만 하면 됨
    // 의존성 주입(DI)

    // /blog/dummy/join 요청
    // http 바디에 username, password, email 데이터를 가지고 요청
    @PostMapping("/dummy/join")
    public String join(User user){ // key = value& ~~(약속됨), www-ur..
        // msg converter가 setter 함수를 이용해 request body 데이터를 자바옵젝에 넣어줌
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());

        userRepository.save(user);
        return "회원가입 완료";
    }
}
