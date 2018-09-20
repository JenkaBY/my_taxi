package com.exposit.my_taxi.service.user;

import com.exposit.my_taxi.model.user.UserActivationEntity;
import com.exposit.my_taxi.model.user.UserEntity;
import com.exposit.my_taxi.service.exception.IncorrectActivationCodeException;

public interface UserActivationService {

    String createAndGetForUser(UserEntity user);

    UserActivationEntity findByCode(String code) throws IncorrectActivationCodeException;

    void delete(UserActivationEntity activation);
}
