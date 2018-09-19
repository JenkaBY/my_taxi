package com.exposit.my_taxi.service.user;

import com.exposit.my_taxi.service.exception.ValidationException;
import com.exposit.my_taxi.service.signup.dto.RegisterUserDto;
import com.exposit.my_taxi.service.user.dto.UserDto;

import java.util.List;
import java.util.Optional;


public interface UserService {
    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    Optional<UserDto> findByEmail(String name);

    UserDto createNewUser(RegisterUserDto user) throws ValidationException;

//    UserDto updateUser(UserDto user);

    boolean isUserExist(String login);
}
