package com.exposit.my_taxi.service.conversion.converter;

import com.exposit.my_taxi.model.user.UserEntity;
import com.exposit.my_taxi.model.user.UserStatus;
import com.exposit.my_taxi.model.user.UserType;
import com.exposit.my_taxi.service.user.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserDtoConverter implements Converter<UserEntity, UserDto> {

    @Override
    public UserDto convert(UserEntity user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getEmail());
        userDto.setUserStatus(UserStatus.getFromLookupCode(user.getUserStatusEntity().getLookupCode()));
        userDto.setUserType(UserType.getFromLookupCode(user.getUserTypeEntity().getLookupCode()));
        return userDto;
    }
}
