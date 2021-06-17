package com.may.jwt.repository;

import com.may.jwt.domain.User;

import java.util.Optional;

public interface UserRepository {

    void save(User user);
    Optional<User> findById(Long id);

}
