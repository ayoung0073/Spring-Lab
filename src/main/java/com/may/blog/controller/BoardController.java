package com.may.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/") // 컨트롤러에서 세션은 어떻게 ?
    public String index(){
        return "index";
        // 머스테치 스타터 덕분에 컨트롤러에서 문자열 반환할 때, 앞의 경로와 뒤의 파일 확장자 자동 지정됨
   }

   @GetMapping("/board/saveForm")
    public String saveForm(){
        return "/board/saveForm";
   }
}
