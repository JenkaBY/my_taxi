package com.exposit.my_taxi.service.user.validation;

import com.exposit.my_taxi.repository.UserRepository;
import com.exposit.my_taxi.service.exception.LoginInUseException;
import com.exposit.my_taxi.service.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LoginValidator {
    private UserRepository userRepository;

    @Autowired
    public LoginValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validate(String login) throws ValidationException {
        validateCharacters(login);
        validateLength(login);
        if (isUserExist(login)) {
            throw new LoginInUseException(login);
        }
    }

    public boolean isUserExist(String login) {
        return Objects.nonNull(userRepository.findByEmail(login));
    }

    public void validateLength(String login) throws ValidationException {
    }

    public void validateCharacters(String login) throws ValidationException {
    }
}
