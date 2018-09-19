package com.exposit.my_taxi.service.user.impl;

import com.exposit.my_taxi.model.user.UserActivationEntity;
import com.exposit.my_taxi.model.user.UserEntity;
import com.exposit.my_taxi.repository.UserActivationRepository;
import com.exposit.my_taxi.service.user.UserActivationService;
import com.exposit.my_taxi.service.utils.ActivationCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserActivationServiceImpl implements UserActivationService {
    private UserActivationRepository activationCodeRepository;
    private ActivationCodeGenerator activationCodeGenerator;

    @Autowired
    public UserActivationServiceImpl(UserActivationRepository activationCodeRepository, ActivationCodeGenerator activationCodeGenerator) {
        this.activationCodeRepository = activationCodeRepository;
        this.activationCodeGenerator = activationCodeGenerator;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public String createNewForUser(UserEntity user) {
        UserActivationEntity activation = new UserActivationEntity();
        activation.setActivationCode(activationCodeGenerator.generate());
        activation.setUser(user);
        activation.setCreatedAt(user.getPasswordUpdatedAt());
        activationCodeRepository.saveAndFlush(activation);
        return activation.getActivationCode();
    }

    @Override
    public UserActivationEntity findByCode(String code) {
        return activationCodeRepository.findByActivationCode(code);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(UserActivationEntity activation) {
        activationCodeRepository.delete(activation);
    }

}
