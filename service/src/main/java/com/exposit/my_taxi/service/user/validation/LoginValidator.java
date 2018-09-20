package com.exposit.my_taxi.service.user.validation;

import com.exposit.my_taxi.service.exception.ValidationException;

public interface LoginValidator {
    void validate(String login) throws ValidationException;
}
