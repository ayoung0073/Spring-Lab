package com.may.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpSession;

@Slf4j
@Aspect
@RequiredArgsConstructor
@Component
public class LoginCheckAspect {

    private final MemberRepository memberRepository;

    @Pointcut("execution(* com.may..*Controller.check*(..))")
    public void loginCheckPointcut() {}

//    @Before("loginCheckPointcut()")
    @Around("@annotation(LoginCheck)") // 다른 패키지에 LoginCheck 인터페이스 정의되어 있으면, 패키지까지 작성
    public void loginCheck(ProceedingJoinPoint pjp) throws Throwable {

        log.info("Login Check");
        HttpSession session = (HttpSession)pjp.getArgs()[0]; // 인자 Object 배열의 첫번째 원소 : HttpSession session
        String id = SessionUtil.getLoginMemberId(session);

        if(id == null) {
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "로그인 실패") {};
        }

        pjp.proceed(new Object[]{session, memberRepository.findById(id).get()});
    }
}
