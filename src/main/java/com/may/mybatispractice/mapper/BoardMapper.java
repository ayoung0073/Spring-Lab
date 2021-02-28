package com.may.mybatispractice.mapper;

import com.may.mybatispractice.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDto> findAll();

    BoardDto findById(Long boardId);

    int save(BoardDto boardDto);

    int delete(Long boardId);

    int update(BoardDto boardDto);
}
