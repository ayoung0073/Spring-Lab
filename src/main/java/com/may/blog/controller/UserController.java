package com.may.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.may.blog.config.auth.PrincipalDetail;
import com.may.blog.model.KakaoProfile;
import com.may.blog.model.OAuthToken;
import com.may.blog.service.UserService;
import com.may.blog.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 /이면 index. 허용
// static 이하에 있는 /js/**. /css/**. /image/** 허용

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Value("${may.key}")
    private String mayKey;

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
    public String kakaoCallback(String code) throws Exception { // Data를 리턴해주는 함수

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

        // Json 데이터를 오브젝트로 바꾸자!
        // Gson라이브러리, Json Simple 라이브러라, ObjectMapper 라이브러리 있음
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);

        // System.out.println("kakao access_token = " + oAuthToken.getAccess_token());

        // POST 방식으로 key=value 데이터 요청(카카오쪽으로)  --> 토큰 생성하는 것
        // 필요한 라이브러리 : RestTemplate
        RestTemplate rt2 = new RestTemplate(); // http 요청 쉽게 할 수 있음 // 원래는 HttpURLConnection을 이용했었음(불편쓰)
        // or Retrofit2, OkHttp, RestTemplate 라이브러리
        // Retrofit : 안드로이드에 많이 사용

        // HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");



        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(headers2);

        // 요청하쟈
        // Http 요청 (Post방식으로, 그리고 response 변수의 응답을 받음)
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest, // Data
                String.class// 응답받을 타입
        );

        System.out.println(response2.getBody());

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);

        // User 오브젝트 : username, password, email
        System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
        System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());

        System.out.println("블로그서버 유저네임 유저네임 : " + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
        System.out.println("블로그서버 이메일 : " + kakaoProfile.getKakao_account().getEmail());

        // UUID : 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘
        // UUID garbagePassword = UUID.randomUUID();
        System.out.println("블로그 서버 패스워드 : " + mayKey);

        User kakaoUser = User.builder()
                .username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
                .password(mayKey)
                .oauth("kakao")
                .email(kakaoProfile.getKakao_account().getEmail())
                .build();


        // 가입자 혹은 비가입자 체크해서 처리
        if (userService.findUser(kakaoUser.getUsername())){
            System.out.println("기존 회원");
        }
        else userService.join(kakaoUser);


        // 로그인 처리
        // 세션 등록 (자동으로 해줄거임) // service에서 하면안됨, 영속화되어있어 서비스가 끝나고 컨트롤러에서 진행해야, 바뀐 패스워드로 진행 가능
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), mayKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";

    }
}
