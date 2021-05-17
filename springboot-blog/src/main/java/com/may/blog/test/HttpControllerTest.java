package com.may.blog.test;

import org.springframework.web.bind.annotation.*;

import javax.management.DescriptorKey;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

    @GetMapping("/http/get")
    public String getTest() {
        return "get요청";
    }

    @PostMapping("/http/post") // text/plain
    public String postTest(@RequestBody String text) {
        return "post 요청 => " + text;
    }

    @PutMapping("/http/put")
    public String putTest() {
        return "put요청";
    }

    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete요청";
    }

}
