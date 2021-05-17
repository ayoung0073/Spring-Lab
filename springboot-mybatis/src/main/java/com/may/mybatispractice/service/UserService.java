package com.may.mybatispractice.service;

import com.may.mybatispractice.dto.UserDto;
import com.may.mybatispractice.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userMapper.findAll();
    }


    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        return userMapper.findById(id);
    }

    @Transactional
    public void save(UserDto userDto) {
        if ((userMapper.nameCheck(userDto.getName())).equals("0"))
            userMapper.save(userDto.getName(), userDto.getAge());
        else
            throw new IllegalArgumentException("이미 존재하는 이름입니다.");
    }
}
