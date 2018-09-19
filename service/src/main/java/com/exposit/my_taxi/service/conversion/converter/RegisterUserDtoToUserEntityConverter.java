package com.exposit.my_taxi.service.conversion.converter;

import com.exposit.my_taxi.model.user.UserEntity;
import com.exposit.my_taxi.service.signup.dto.RegisterUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserDtoToUserEntityConverter implements Converter<RegisterUserDto, UserEntity> {
    private UserStatusToEntityConverter userStatusToEntityConverter;
    private UserTypeToEntityConverter userTypeToEntityConverter;

    @Autowired
    public RegisterUserDtoToUserEntityConverter(UserStatusToEntityConverter userStatusToEntityConverter,
                                                UserTypeToEntityConverter userTypeToEntityConverter) {
        this.userStatusToEntityConverter = userStatusToEntityConverter;
        this.userTypeToEntityConverter = userTypeToEntityConverter;
    }

    @Override
    public UserEntity convert(RegisterUserDto user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setUserStatusEntity(userStatusToEntityConverter.convert(user.getUserStatus()));
        userEntity.setUserTypeEntity(userTypeToEntityConverter.convert(user.getUserType()));
        return userEntity;
    }
}
