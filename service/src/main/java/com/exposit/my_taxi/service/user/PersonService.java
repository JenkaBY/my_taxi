package com.exposit.my_taxi.service.user;

import com.exposit.my_taxi.service.user.dto.SignupDto;
import com.exposit.my_taxi.service.user.dto.UserDto;

import java.util.List;


public interface PersonService {

    UserDto findById(Long id);

    void deleteById(Long id);

    UserDto update(UserDto userDto);

    List<UserDto> findAll();

    UserDto createCustomer(SignupDto credential);

    UserDto createAdmin(SignupDto credential);
}
