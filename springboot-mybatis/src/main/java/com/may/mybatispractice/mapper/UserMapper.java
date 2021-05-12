package com.may.mybatispractice.mapper;

import com.may.mybatispractice.dto.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserDto> findAll();

    UserDto findById(Long id);

    String nameCheck(String id);

    void save(@Param("name") String name, @Param("age") int age);
}
