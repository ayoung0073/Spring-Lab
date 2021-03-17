package com.may.aop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/register")
    public ResponseEntity<String> registerSession(HttpServletRequest request, @RequestBody MemberDto memberDto) {
        SessionUtil.setLoginMemberId(request.getSession(), memberDto.getId());
        memberService.saveMember(memberDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

//    @GetMapping("/api/check") // AOP 적용 전
//    public ResponseEntity<MemberDto> checkSession(HttpSession session) {
//        ResponseEntity<MemberDto> response = null;
//        String id = SessionUtil.getLoginMemberId(session);
//        if (id == null) {
//            response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        } else {
//            MemberDto member = memberService.getInfo(id);
//            response = new ResponseEntity<>(member, HttpStatus.OK);
//        }
//        return response;
//    }

    @GetMapping("/api/check") // AOP 적용 후
    @LoginCheck
    public ResponseEntity<MemberDto> checkSession(HttpSession session) {
        String id = SessionUtil.getLoginMemberId(session);
        MemberDto member = memberService.getInfo(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

}
