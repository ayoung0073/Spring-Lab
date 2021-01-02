package com.may.blog.service;

import com.may.blog.model.Board;
import com.may.blog.model.User;
import com.may.blog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void register(Board board, User user){
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> getBoardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    public Board getBoardDetail(Long id){
        return boardRepository.findById(id).orElseThrow(()
        ->{return new IllegalArgumentException("해당 게시글이 없습니다");});
    }

    @Transactional
    public void deleteBoard(Long id){
        boardRepository.deleteById(id);
    }

//    @Transactional(readOnly = true)
//    public boolean checkWriter(Long id, Long userId){
//        if(boardRepository.findById(id).)
//    }

}
