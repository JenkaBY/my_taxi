package com.exposit.my_taxi.service.conversion.converter;

import com.exposit.my_taxi.model.profile.ProfileEntity;
import com.exposit.my_taxi.service.user.dto.ProfileDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfileDtoToEntityConverter implements Converter<ProfileDto, ProfileEntity> {
    @Override
    public ProfileEntity convert(ProfileDto dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setId(dto.getId());
        return entity;
    }
}
