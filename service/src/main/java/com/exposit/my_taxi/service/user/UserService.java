package com.exposit.my_taxi.service.user;

import com.exposit.my_taxi.service.user.dto.UserDto;

import java.util.List;
import java.util.Optional;


public interface UserService {
    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    Optional<UserDto> findByName(String name);

    Optional<UserDto> createNewUser(UserDto user);

    UserDto updateUser(UserDto user);

    boolean isUserNameExist(String name);
}
