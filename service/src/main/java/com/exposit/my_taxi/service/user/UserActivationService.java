package com.exposit.my_taxi.service.user;

import com.exposit.my_taxi.model.user.UserActivationEntity;
import com.exposit.my_taxi.model.user.UserEntity;

public interface UserActivationService {

    String createNewForUser(UserEntity user);

    UserActivationEntity findByCode(String code);

    void delete(UserActivationEntity activation);
}
