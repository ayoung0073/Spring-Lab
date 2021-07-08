package com.may.mapstruct;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-12T20:38:02+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 14.0.2 (AdoptOpenJDK)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity signUpDtoToEntity(SignUpDto signUpDto) {
        if ( signUpDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        return userEntity;
    }

    @Override
    public UserProfileDto toProfileDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserProfileDto userProfileDto = new UserProfileDto();

        return userProfileDto;
    }
}
