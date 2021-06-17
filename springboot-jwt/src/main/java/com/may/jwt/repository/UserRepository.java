package com.may.jwt.repository;

import com.may.jwt.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {

    User save(User user);
    Optional<User> findById(Long id);

}
