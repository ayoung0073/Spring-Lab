package com.may.springbootmongodb.repository;

import com.may.springbootmongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
    Long deleteByName(String name);
}
