package com.may.blog.repository;

import com.may.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// DAO
// 자동으로 Bean 등록 됨
// @Repository // 생략 가능
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    // JPA Naming 쿼리
    // SELECT * FROM user WHERE username = ? AND password = ?;
    // User findByUsernameAndPassword(String username, String password);

//    @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//    User login(String username, String password);
}
