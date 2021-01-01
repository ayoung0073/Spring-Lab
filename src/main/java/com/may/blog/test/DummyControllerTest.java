package com.may.blog.test;

import com.may.blog.model.RoleType;
import com.may.blog.model.User;
import com.may.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

// html이 아니라 data를 응답해주는 컨트롤러
@RequiredArgsConstructor
@RestController
public class DummyControllerTest {

    private final UserRepository userRepository;
    // 스프링이 componentScan할 때, UserRepository를 메모리에 올려두기 때문에, 사용하기만 하면 됨
    // 의존성 주입(DI)

    // {id} 주소로 파라미터를 전달 받을 수 있음
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable Long id){
        User user = userRepository.findById(id).orElseThrow( // 람다식
                        () -> new IllegalArgumentException("해당 유저 존재하지 않습니다."));
//        return user.orElseGet(new Supplier<User>() {
//            @Override
//            public User get() {
//                return new User();// 빈 객체를 user로,,(널 아님)
//            }
//        });

        // 스프링부트 = MessageConverter라는 애가 응답 시에 자동 작동
        // 만약 자바 오브젝트를 리턴하면, msgConverter가 Jackson 라이브러리를 호출해서
        // json으로 변환해 브라우저에게 던져줌
        return user;
    }


    // /blog/dummy/join 요청
    // http 바디에 username, password, email 데이터를 가지고 요청
    @PostMapping("/dummy/join")
    public String join(User user){ // key = value& ~~(약속됨), www-ur..
        // msg converter가 setter 함수를 이용해 request body 데이터를 자바옵젝에 넣어줌
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());

        user.setRole(RoleType.USER);

        userRepository.save(user);
        return "회원가입 완료";
    }
}
