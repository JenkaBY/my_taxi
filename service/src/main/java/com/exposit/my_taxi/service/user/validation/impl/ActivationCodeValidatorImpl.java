package com.exposit.my_taxi.service.user.validation.impl;

import com.exposit.my_taxi.model.user.UserActivationEntity;
import com.exposit.my_taxi.repository.UserActivationRepository;
import com.exposit.my_taxi.service.exception.IncorrectActivationCodeException;
import com.exposit.my_taxi.service.user.validation.ActivationCodeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class ActivationCodeValidatorImpl implements ActivationCodeValidator {
    private static final String INCORRECT_ACTIVATION_CODE = "Incorrect activation code : ";
    private static final String USER_HAS_BEEN_ACTIVATED = " Or User has been activated yet.";
    private static final String ACTIVATION_CODE_IS_EXPIRE = "Activation code is expire. Sign up again.";
    @Value("${activation.code.expiry.day}")
    private int NUM_OF_DAYS_ACTIVITY_ACTIVATION_CODE;
    private UserActivationRepository activationRepository;

    @Autowired
    public ActivationCodeValidatorImpl(UserActivationRepository activationRepository) {
        this.activationRepository = activationRepository;
    }

    @Override
    public UserActivationEntity validate(String activationCode) throws IncorrectActivationCodeException {
        validateActivationCode(activationCode);
        UserActivationEntity activation = activationRepository.findByActivationCode(activationCode);
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
            throw new IncorrectActivationCodeException(ACTIVATION_CODE_IS_EXPIRE);
        }
    }

    private static long betweenDates(Instant now, Instant activationCreatedAt) {
        return ChronoUnit.DAYS.between(now, activationCreatedAt);
    }
}
