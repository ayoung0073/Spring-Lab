package com.may.blog.repository;

import com.may.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    //Page<Board> findAll(Pageable pageable);
}
