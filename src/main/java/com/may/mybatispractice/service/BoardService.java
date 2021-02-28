package com.may.mybatispractice.service;

import com.may.mybatispractice.dto.BoardDto;
import com.may.mybatispractice.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardMapper boardMapper;

    @Transactional
    public Long save(BoardDto boardDto){
        if(boardMapper.save(boardDto) == 0){
            throw new IllegalArgumentException("데이터베이스에 저장되지 않았습니다.");
        }
        return boardDto.getBoardId();
    }

    @Transactional(readOnly = true)
    public List<BoardDto> findAll(){
        return boardMapper.findAll();
    }


    @Transactional(readOnly = true)
    public BoardDto findById(Long id){
        return boardMapper.findById(id);
    }

    @Transactional
    public int update(Long boardId, BoardDto boardDto){
        boardDto.setBoardId(boardId);
        return boardMapper.update(boardDto);
    }

    @Transactional
    public void delete(Long boardId) {
        if (boardMapper.delete(boardId) == 0) { // 삭제 개수 return
            throw new IllegalArgumentException("삭제 실패");
        }
    }
}
