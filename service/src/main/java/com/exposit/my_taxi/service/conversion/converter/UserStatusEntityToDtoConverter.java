package com.exposit.my_taxi.service.conversion.converter;

import com.exposit.my_taxi.model.user.UserStatusEntity;
import com.exposit.my_taxi.service.user.dto.UserStatusDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserStatusEntityToDtoConverter implements Converter<UserStatusEntity, UserStatusDto> {

    @Override
    public UserStatusDto convert(UserStatusEntity entity) {
        UserStatusDto type = new UserStatusDto();
        type.setId(entity.getId());
        type.setTitle(entity.getTitle());
        type.setLookupCode(entity.getLookupCode());
        return type;
    }
}
