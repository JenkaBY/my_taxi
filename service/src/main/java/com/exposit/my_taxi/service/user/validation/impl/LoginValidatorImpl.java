package com.exposit.my_taxi.service.user.validation.impl;

import com.exposit.my_taxi.repository.UserRepository;
import com.exposit.my_taxi.service.exception.LoginInUseException;
import com.exposit.my_taxi.service.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LoginValidatorImpl implements com.exposit.my_taxi.service.user.validation.LoginValidator {
    private UserRepository userRepository;

    @Autowired
    public LoginValidatorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(String login) throws ValidationException {
        validateCharacters(login);
        validateLength(login);
        if (isUserExist(login)) {
            throw new LoginInUseException(login);
        }
    }

    public boolean isUserExist(String email) {
        return Objects.nonNull(userRepository.findByEmail(email));
    }

    public void validateLength(String login) throws ValidationException {
    }

    public void validateCharacters(String login) throws ValidationException {
    }
}
