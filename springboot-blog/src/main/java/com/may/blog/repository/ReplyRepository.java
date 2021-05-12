package com.may.blog.repository;

import com.may.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Modifying
    @Query(value = "INSERT INTO reply(userId, boardId, content, createdAt) VALUES (?1, ?2, ?3, now())", nativeQuery = true)
    int mSave(Long userId, Long boardId, String content); // 업데이트된 행의 개수 리턴 // @Modifying 은 int만 return
}
