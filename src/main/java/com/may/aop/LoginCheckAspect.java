package com.may.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class LoginCheckAspect {

    @Pointcut("execution(* com.may..*Controller(..))")
    public void loginCheckPointcut() {}

    @Before("loginCheckPointcut()")
    public void loginCheck(JoinPoint jp) {

        HttpSession session = (HttpSession)jp.getArgs()[0]; // 인자 Object 배열의 첫번째 원소 : HttpSession session
        String id = SessionUtil.getLoginMemberId(session);

        if(id == null) {
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "로그인 실패") {};
        }

    }
}
