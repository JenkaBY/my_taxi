package com.exposit.my_taxi.service.user.impl;

import com.exposit.my_taxi.model.user.UserActivationEntity;
import com.exposit.my_taxi.model.user.UserEntity;
import com.exposit.my_taxi.repository.UserActivationRepository;
import com.exposit.my_taxi.service.exception.IncorrectActivationCodeException;
import com.exposit.my_taxi.service.user.UserActivationService;
import com.exposit.my_taxi.service.user.validation.ActivationCodeValidator;
import com.exposit.my_taxi.service.utils.ActivationCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserActivationServiceImpl implements UserActivationService {
    private UserActivationRepository activationCodeRepository;
    private ActivationCodeGenerator activationCodeGenerator;
    private ActivationCodeValidator activationCodeValidator;

    @Autowired
    public UserActivationServiceImpl(UserActivationRepository activationCodeRepository,
                                     ActivationCodeGenerator activationCodeGenerator,
                                     ActivationCodeValidator activationCodeValidator) {
        this.activationCodeRepository = activationCodeRepository;
        this.activationCodeGenerator = activationCodeGenerator;
        this.activationCodeValidator = activationCodeValidator;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public String createAndGetForUser(UserEntity user) {
        UserActivationEntity activation = new UserActivationEntity();
        activation.setActivationCode(activationCodeGenerator.generate());
        activation.setUser(user);
        activation.setCreatedAt(user.getPasswordUpdatedAt());
        activationCodeRepository.saveAndFlush(activation);
        return activation.getActivationCode();
    }

    @Override
    public UserActivationEntity findByCode(String code) throws IncorrectActivationCodeException {
        activationCodeValidator.validate(code);
        return activationCodeRepository.findByActivationCode(code);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(UserActivationEntity activation) {
        activationCodeRepository.delete(activation);
    }

}
