package com.may.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
// 해당 어노테이션이 있어야 MapStruct 활용 가능 // (componentModel = "spring") -> 스프링 컨테이너에서 객체 관리
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    UserEntity signUpDtoToEntity(SignUpDto signUpDto);
}
