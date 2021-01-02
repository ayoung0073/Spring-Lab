package com.may.blog.controller;

import com.may.blog.config.auth.PrincipalDetail;
import com.may.blog.model.Board;
import com.may.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/") // 컨트롤러에서 세션은 어떻게 ? => @AuthenticationPrincipal PrincipalDetail principal
    public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        // 최신 글부터
        model.addAttribute("boardList", boardService.getBoardList(pageable));
        return "index";
        // 머스테치 스타터 덕분에 컨트롤러에서 문자열 반환할 때, 앞의 경로와 뒤의 파일 확장자 자동 지정됨
   }

   @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm"; // "/board/saveForm" 하면 안됨
   }
}
