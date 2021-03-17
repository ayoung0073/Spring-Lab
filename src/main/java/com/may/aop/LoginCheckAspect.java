package com.may.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpSession;

@Slf4j
@Aspect
@Component
public class LoginCheckAspect {

    @Pointcut("execution(* com.may..*Controller.check*(..))")
    public void loginCheckPointcut() {}

//    @Before("loginCheckPointcut()")
    @Before("@annotation(LoginCheck)") // 다른 패키지에 LoginCheck 인터페이스 정의되어 있으면, 패키지까지 작성
    public void loginCheck(JoinPoint jp) {

        log.info("Login Check");
        HttpSession session = (HttpSession)jp.getArgs()[0]; // 인자 Object 배열의 첫번째 원소 : HttpSession session
        String id = SessionUtil.getLoginMemberId(session);

        if(id == null) {
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "로그인 실패") {};
        }

    }
}
