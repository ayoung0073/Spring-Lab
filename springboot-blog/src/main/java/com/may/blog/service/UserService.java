package com.may.blog.service;

import com.may.blog.model.RoleType;
import com.may.blog.model.User;
import com.may.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
// 스프링이 컴포넌트 스캔을 통해 Bean에 등록 해줌, IoC
@Service
public class UserService {
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public boolean findUser(String username){
        if(userRepository.findByUsername(username).isPresent()) return true;
        return false;
    }

    @Transactional
    public int join(User user) throws Exception{
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        user.setRole(RoleType.USER);

        String rawPassword = user.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encPassword);

        userRepository.save(user);

        return 1;
    }

    @Transactional
    public void updateUser(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 수정 시, 영속성 컨텍스트 user 오브젝트를 영속화시키고, 영속화된 User 객체를 수정
        // select를 해서 User 오브젝트를 DB로부터 가져오는 이유는 영속화를 하려고
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줌
        User persistance = userRepository.findById(user.getId()).orElseThrow(()
        ->{return new IllegalArgumentException("회원 찾기에 실패했습니다");
        });

        // Validete 체크
        if(persistance.getOauth() == null || persistance.getOauth().equals("")){
            String rawPassword = user.getPassword();
            String encPassword = passwordEncoder.encode(rawPassword);
            persistance.setPassword(encPassword);
            persistance.setEmail(user.getEmail());
        }

        // 회원 수정 함수 종료시 == 서비스 종료 == 트랜잭션 종료 == 커밋 자동으로 됨
        // 영속화된  persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.
    }

//    @Transactional(readOnly = true) // select 할 때 트랜잭션 시작, 서비스 종료 시에 트랜잭션 종료(정합성)
//    public User login(User user) throws Exception{
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
}
