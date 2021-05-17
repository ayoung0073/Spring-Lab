package com.may.blog.config.auth;

import com.may.blog.model.User;
import com.may.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // 스프링이 로그인 요청을 가로챌 때, suername, password 변수 2개를 가로챌때 password 알아서 처리
    // username이 DB에 있는지만 우리가 확인해주기 !
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username).orElseThrow(() -> {
            return new UsernameNotFoundException("해당 사용자 찾을 수 없습니다");
        });
        return new PrincipalDetail(principal); // 시큐리티 세션에 유저 정보 저장됨 // 아이디:user, 패스워드: 콘솔창
    }
}
