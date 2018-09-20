package com.exposit.my_taxi.service.user.validation;

import com.exposit.my_taxi.model.user.UserActivationEntity;
import com.exposit.my_taxi.service.exception.IncorrectActivationCodeException;
import com.exposit.my_taxi.service.user.UserActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class ActivationCodeValidator {
    private static final String INCORRECT_ACTIVATION_CODE = "Incorrect activation code : ";
    private static final String USER_HAS_BEEN_ACTIVATED = System.lineSeparator() + "Or User has been activated yet.";
    public static final int NUM_OF_DAYS_ACTIVITY_ACTIVATION_CODE = 1;
    private UserActivationService activationService;

    @Autowired
    public ActivationCodeValidator(UserActivationService activationService) {
        this.activationService = activationService;
    }

    public UserActivationEntity validate(String activationCode) throws IncorrectActivationCodeException {
        validateActivationCode(activationCode);
        UserActivationEntity activation = activationService.findByCode(activationCode);
        validatePresent(activation, activationCode);
        validateExpiryDate(activation);
        return activation;
    }

    private void validateActivationCode(String activationCode) throws IncorrectActivationCodeException {
        if (activationCode == null || activationCode.isEmpty()) {
            throw new IncorrectActivationCodeException(INCORRECT_ACTIVATION_CODE + activationCode);
        }
    }

    private void validatePresent(UserActivationEntity activationEntity, String activationCode) throws IncorrectActivationCodeException {
        if (activationEntity == null) {
            throw new IncorrectActivationCodeException(INCORRECT_ACTIVATION_CODE + activationCode + USER_HAS_BEEN_ACTIVATED);
        }
    }

    private void validateExpiryDate(UserActivationEntity activationEntity) throws IncorrectActivationCodeException {
        if (betweenDates(Instant.now(), activationEntity.getCreatedAt().toInstant()) >= NUM_OF_DAYS_ACTIVITY_ACTIVATION_CODE) {
            throw new IncorrectActivationCodeException("Activation code is expire. Sign up again.");
        }
    }

    private static long betweenDates(Instant now, Instant activationCreatedAt) {
        return ChronoUnit.DAYS.between(now, activationCreatedAt);
    }
}
