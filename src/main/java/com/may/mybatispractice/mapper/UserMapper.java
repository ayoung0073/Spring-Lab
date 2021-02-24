package com.may.mybatispractice.mapper;

import com.may.mybatispractice.dto.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

//    @Select("SELECT * FROM user")
    List<UserDto> findAll();

//    @Select("SELECT * FROM user where id = #{id}")
    UserDto findById(Long id);

    String nameCheck(String id);

    //    @Insert("INSERT INTO user (name, age) values (#{name}, #{age})")
    void save(@Param("name") String name, @Param("age") int age);
}
