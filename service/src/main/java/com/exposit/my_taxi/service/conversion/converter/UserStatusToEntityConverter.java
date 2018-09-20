package com.exposit.my_taxi.service.conversion.converter;

import com.exposit.my_taxi.model.user.UserStatus;
import com.exposit.my_taxi.model.user.UserStatusEntity;
import com.exposit.my_taxi.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserStatusToEntityConverter implements Converter<UserStatus, UserStatusEntity> {
    private UserStatusRepository userStatusRepository;

    @Autowired
    public UserStatusToEntityConverter(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    @Override
    public UserStatusEntity convert(UserStatus userStatus) {
        UserStatusEntity entity = userStatusRepository.findByLookupCode(userStatus.getLookupCode());
        return entity;
    }
}
