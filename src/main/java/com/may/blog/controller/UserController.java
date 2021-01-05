package com.may.blog.controller;

import com.may.blog.config.auth.PrincipalDetail;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 /이면 index. 허용
// static 이하에 있는 /js/**. /css/**. /image/** 허용

@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String joinForm(){
        return "/user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(){
        return "/user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm(Model model, @AuthenticationPrincipal PrincipalDetail principal){
        model.addAttribute("principal", principal);
        return "/user/updateForm";
    }

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code){ // Data를 리턴해주는 함수

        // --> code를 받으면 인증 성공

        // https://kauth.kakao.com/oauth/token
        //grant_type=authorization_code
        //client_id=5354f1c1ec547c553355a726f5d4b288
        //redirect_uri=http://localhost:8080/auth/kakao/callback
        //code={동적임}

        // POST 방식으로 key=value 데이터 요청(카카오쪽으로)  --> 토큰 생성하는 것
        // 필요한 라이브러리 : RestTemplate
        RestTemplate rt = new RestTemplate(); // http 요청 쉽게 할 수 있음 // 원래는 HttpURLConnection을 이용했었음(불편쓰)
        // or Retrofit2, OkHttp, RestTemplate 라이브러리
        // Retrofit : 안드로이드에 많이 사용

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id","5354f1c1ec547c553355a726f5d4b288");
        params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
        params.add("code", code);

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
            new HttpEntity<>(params, headers);

        // 요청하쟈
        // Http 요청 (Post방식으로, 그리고 response 변수의 응답을 받음)
        ResponseEntity<String> response = rt.exchange(
            "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest, // Data
                String.class// 응답받을 타입
        );


        //return "카카오 인증 성공 : 코드값 - " +  code;
        return "카카오 토큰 요청 성공 : 토큰 요청에 대한 응답<br>" + response;

    }
}
