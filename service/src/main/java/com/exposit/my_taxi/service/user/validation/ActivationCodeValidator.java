package com.exposit.my_taxi.service.user.validation;

import com.exposit.my_taxi.model.user.UserActivationEntity;
import com.exposit.my_taxi.service.exception.IncorrectActivationCodeException;

public interface ActivationCodeValidator {
    UserActivationEntity validate(String activationCode) throws IncorrectActivationCodeException;
}
