package com.may.blog.config;

import com.may.blog.config.auth.PrincipalDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 함
@Configuration // IoC 관리
@RequiredArgsConstructor
@EnableWebSecurity // 스프링시큐리티 필터 등록 : 스프링 시큐리티가 활성화 되어있는데 어떤 설정을 해당 파일에서 하겠다
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalDetailService principalDetailService;

    @Bean // IoC
    public PasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    // 시큐리티가 대신 로그인해주는데 password를 가로채기할 때
    // 해당 password가 뭘로 해시가 되어 회원가입 되어있는지 알아야
    // 같은 해시로 암호화해서 DB에 있는 해시랑 비교할 수 있음




    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
//                .headers().frameOptions().disable()
//                .and()
                .authorizeRequests()
                    .antMatchers( "/", "/auth/**", "/js/**", "/css/**", "/image/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm")
                    .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청 오는 로그인을 가로채서 대신 로그인해줌
                    .defaultSuccessUrl("/")
                    .failureUrl("/auth/joinForm");
   }


}
