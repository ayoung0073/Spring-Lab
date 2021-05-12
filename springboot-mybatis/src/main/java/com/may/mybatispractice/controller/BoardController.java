package com.may.mybatispractice.controller;

import com.may.mybatispractice.dto.BoardDto;
import com.may.mybatispractice.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseEntity<?> saveUser(@RequestBody BoardDto boardDto) {
        return new ResponseEntity<>(boardService.save(boardDto), HttpStatus.CREATED);
    }

    @GetMapping("/boards")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(boardService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<?> findById(@PathVariable Long boardId) {
        return new ResponseEntity<>(boardService.findById(boardId), HttpStatus.OK);
    }

    @PutMapping("/board/{boardId}")
    public ResponseEntity<?> updateById(@PathVariable Long boardId, @RequestBody BoardDto boardDto) {
        if (boardService.update(boardId, boardDto) != 0){
            throw new IllegalArgumentException("게시글 수정 실패");
        }
        return ResponseEntity.ok("게시글 수정 성공");
    }


    @DeleteMapping("/board/{boardId}")
    public ResponseEntity<?> deleteById(@PathVariable Long boardId) {
        boardService.delete(boardId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
