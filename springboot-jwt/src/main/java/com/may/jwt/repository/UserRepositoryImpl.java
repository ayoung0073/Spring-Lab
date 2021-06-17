package com.may.jwt.repository;

import com.may.jwt.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// 회원 레포지토리 메모리 구현체
@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final Map<Long, User> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public void save(User user) {
        user.setId(++sequence);
        store.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public void clearStore() {
        store.clear();
    }

}
