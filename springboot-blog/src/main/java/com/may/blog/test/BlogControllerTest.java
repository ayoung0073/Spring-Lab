package com.may.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Spring이 com.may.blog 패키지 이하를 스캔해서 모든 파일을 메모리에 new하는 것이 아니라,
// 특정 Annotation이 붙어있는 클래스 파일들을 new해서(IoC) 스프링 컨테이너에 관리
@RestController
public class BlogControllerTest {

    @GetMapping("/test/hello")
    public String hello() {
        return "<h1>hello 스프링부트</h2>";
    }
}
