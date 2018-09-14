package com.exposit.my_taxi.service.conversion.converter;

import com.exposit.my_taxi.model.user.UserTypeEntity;
import com.exposit.my_taxi.service.user.dto.UserTypeDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserTypeEntityToDtoConverter implements Converter<UserTypeEntity, UserTypeDto> {

    @Override
    public UserTypeDto convert(UserTypeEntity userType) {
        UserTypeDto type = new UserTypeDto();
        type.setId(userType.getId());
        type.setTitle(userType.getTitle());
        type.setLookupCode(userType.getLookupCode());
        return type;
    }
}
