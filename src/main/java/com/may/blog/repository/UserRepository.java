package com.may.blog.repository;

import com.may.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// DAO
// 자동으로 Bean 등록 됨
// @Repository // 생략 가능
public interface UserRepository extends JpaRepository<User, Long> {
}
