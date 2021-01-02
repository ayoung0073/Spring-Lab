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

    @Transactional
    public void updateBoard(Long id, Board requestBoard){
        Board board= boardRepository.findById(id).orElseThrow(
                () ->{return new IllegalArgumentException("글을 찾을 수 없습니다");
                }); // 영속화 완료
        System.out.println(requestBoard.getContent());
        board.update(requestBoard.getTitle(), requestBoard.getContent());
        // 해당 함수 종료 시(Service) 트랜잭션 종료됨. 이때 더티체킹돼서 자동 업뎃됨(db flush)

    }

//    @Transactional(readOnly = true)
//    public boolean checkWriter(Long id, Long userId){
//        if(boardRepository.findById(id).)
//    }

}
