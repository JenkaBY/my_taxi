package com.exposit.my_taxi.service.conversion.converter;

import com.exposit.my_taxi.model.user.UserType;
import com.exposit.my_taxi.model.user.UserTypeEntity;
import com.exposit.my_taxi.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserTypeToEntityConverter implements Converter<UserType, UserTypeEntity> {
    private UserTypeRepository userTypeRepository;

    @Autowired
    public UserTypeToEntityConverter(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public UserTypeEntity convert(UserType userType) {
        UserTypeEntity entity = userTypeRepository.findUserTypeEntityByLookupCode(userType.getLookupCode());
        return entity;
    }
}
