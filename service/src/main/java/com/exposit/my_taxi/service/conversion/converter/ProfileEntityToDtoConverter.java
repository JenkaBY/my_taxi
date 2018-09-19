package com.exposit.my_taxi.service.conversion.converter;

import com.exposit.my_taxi.model.profile.ProfileEntity;
import com.exposit.my_taxi.service.user.dto.ProfileDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfileEntityToDtoConverter implements Converter<ProfileEntity, ProfileDto> {
    @Override
    public ProfileDto convert(ProfileEntity entity) {
        ProfileDto dto = new ProfileDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setId(entity.getId());
        return dto;
    }
}
