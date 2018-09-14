package com.exposit.my_taxi.service.user;

import com.exposit.my_taxi.service.exception.ValidationException;
import com.exposit.my_taxi.service.signup.dto.RegisteredUserDto;
import com.exposit.my_taxi.service.user.dto.UserDto;

import java.util.List;
import java.util.Optional;


public interface UserService {
    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    Optional<UserDto> findByLogin(String name);

    UserDto createNewUser(RegisteredUserDto user) throws ValidationException;

//    UserDto updateUser(UserDto user);

    boolean isUserExist(String login);
}
