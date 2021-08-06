package com.may.springrestdocs.controller;

import com.may.springrestdocs.dto.BoardRequest;
import com.may.springrestdocs.dto.BoardResponse;
import com.may.springrestdocs.dto.ResponseDto;
import com.may.springrestdocs.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, "success to get board", boardService.get(id)));
    }

    @PostMapping
    public ResponseEntity<?> saveBoard(@RequestBody BoardRequest request) {
        boardService.save(request);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, "success to save board"));
    }
}
