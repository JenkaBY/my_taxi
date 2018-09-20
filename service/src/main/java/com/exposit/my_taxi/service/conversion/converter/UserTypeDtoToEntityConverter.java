package com.exposit.my_taxi.service.conversion.converter;

import com.exposit.my_taxi.model.user.UserTypeEntity;
import com.exposit.my_taxi.repository.UserTypeRepository;
import com.exposit.my_taxi.service.user.dto.UserTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserTypeDtoToEntityConverter implements Converter<UserTypeDto, UserTypeEntity> {
    private UserTypeRepository userTypeRepository;

    @Autowired
    public UserTypeDtoToEntityConverter(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public UserTypeEntity convert(UserTypeDto userType) {
        UserTypeEntity entity = userTypeRepository.findByLookupCode(userType.getLookupCode());
        return entity;
    }
}
