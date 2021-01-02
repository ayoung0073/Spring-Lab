package com.may.blog.config.auth;

import com.may.blog.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장 해줌
@RequiredArgsConstructor
public class PrincipalDetail implements UserDetails {
    private User user; // composition

    public PrincipalDetail(User user){
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정 만료되지 않았는지 리턴(true:만료 X)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠겨있지 않았는지 리턴(true: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료되지 않았는지 리턴(true: 만료안댐)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화(사용가능) 체크
    @Override
    public boolean isEnabled() {
        return true;
    }


    // 계정이 갖고있는 권한 목록 리턴(권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 우리는 한개임)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();

//        collectors.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return "ROLE_" + user.getRole(); // ROLE_ 꼭 넣어줘야 함.
//            }
//        });
//        같은 뜻
        collectors.add(()->{ return "ROLE_" + user.getRole();});
        return collectors;
    }


}
