package com.exposit.my_taxi.service.user;

import com.exposit.my_taxi.service.user.dto.UserDtoOld;

import java.util.List;
import java.util.Optional;


public interface UserService {
    List<UserDtoOld> findAll();

    Optional<UserDtoOld> findById(Long id);

    Optional<UserDtoOld> findByName(String name);

    Optional<UserDtoOld> createNewUser(UserDtoOld user);

    UserDtoOld updateUser(UserDtoOld user);

    boolean isUserLoginExist(String name);
}
