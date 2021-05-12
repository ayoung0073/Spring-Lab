package com.may.blog.test;

import com.may.blog.model.RoleType;
import com.may.blog.model.User;
import com.may.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
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
    public User detail(@PathVariable Long id) {
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

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한 페이지당 2개의 데이터를 리턴
    // /dummy/user/page?page=0 0부터 시작,
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) { // id를 기준으로 내림차순
        Page<User> pagingUser = userRepository.findAll(pageable);
        // if(pagingUser.isLast()){} // 메서드 제공
        List<User> users = pagingUser.getContent(); // User 정보만
        return users;
    }

    // save는 id 전달안하면 insert, 있으면 update
    // 전달했는데 id에 대한 정보 없으면 insert
    // email, password
    @Transactional // 함수 종료 시에 자동 commit 됨
    @PutMapping("/dummy/user/{id}")
    public Long updateUser(@PathVariable Long id, @RequestBody User requestUser) {
        System.out.println("id: " + id);
        System.out.println("password: " + requestUser.getPassword());
        System.out.println("email: " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 유저 존재 X"));

        user.setEmail(requestUser.getEmail());
        user.setPassword(requestUser.getPassword());

        // userRepository.save(user);
        // save를 하지 않아도 변경됨

        // 더티 체킹 -> 노션
        return id;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable Long id){
        if(userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return "삭제 되었습니다";
        }
        return "삭제 실패"; // EmptyResultDataAccessException
    }


    // /blog/dummy/join 요청
    // http 바디에 username, password, email 데이터를 가지고 요청
    @PostMapping("/dummy/join")
    public String join(User user) { // key = value& ~~(약속됨), www-ur..
        // msg converter가 setter 함수를 이용해 request body 데이터를 자바옵젝에 넣어줌
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());

        user.setRole(RoleType.USER);

        userRepository.save(user);
        return "회원가입 완료";
    }
}
