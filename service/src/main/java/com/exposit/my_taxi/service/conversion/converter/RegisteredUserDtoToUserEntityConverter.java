package com.exposit.my_taxi.service.conversion.converter;

import com.exposit.my_taxi.model.user.UserEntity;
import com.exposit.my_taxi.service.signup.dto.RegisteredUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegisteredUserDtoToUserEntityConverter implements Converter<RegisteredUserDto, UserEntity> {
    private UserStatusToEntityConverter userStatusToEntityConverter;
    private UserTypeToEntityConverter userTypeToEntityConverter;

    @Autowired
    public RegisteredUserDtoToUserEntityConverter(UserStatusToEntityConverter userStatusToEntityConverter,
                                                  UserTypeToEntityConverter userTypeToEntityConverter) {
        this.userStatusToEntityConverter = userStatusToEntityConverter;
        this.userTypeToEntityConverter = userTypeToEntityConverter;
    }

    @Override
    public UserEntity convert(RegisteredUserDto user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(user.getLogin());
        userEntity.setUserStatusEntity(userStatusToEntityConverter.convert(user.getUserStatus()));
        userEntity.setUserTypeEntity(userTypeToEntityConverter.convert(user.getUserType()));
        return userEntity;
    }
}
