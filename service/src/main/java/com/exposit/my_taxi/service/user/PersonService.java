package com.exposit.my_taxi.service.user;

import com.exposit.my_taxi.service.user.dto.PersonDto;
import com.exposit.my_taxi.service.user.dto.SignupDto;

import java.util.List;


public interface PersonService {

    PersonDto findById(Long id);

    void deleteById(Long id);

    PersonDto update(PersonDto personDto);

    List<PersonDto> findAll();

    PersonDto createCustomer(SignupDto credential);

    PersonDto createAdmin(SignupDto credential);
}
