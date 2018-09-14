package com.exposit.my_taxi.service.conversion.converter;

import com.exposit.my_taxi.model.user.UserStatusEntity;
import com.exposit.my_taxi.repository.UserStatusRepository;
import com.exposit.my_taxi.service.user.dto.UserStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserStatusDtoToEntityConverter implements Converter<UserStatusDto, UserStatusEntity> {
    private UserStatusRepository userStatusRepository;

    @Autowired
    public UserStatusDtoToEntityConverter(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    @Override
    public UserStatusEntity convert(UserStatusDto userType) {
        UserStatusEntity entity = userStatusRepository.findUserStatusEntityByLookupCode(userType.getLookupCode());
        return entity;
    }
}
