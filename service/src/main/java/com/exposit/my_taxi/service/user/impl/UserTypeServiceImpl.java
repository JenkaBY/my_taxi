package com.exposit.my_taxi.service.user.impl;

import com.exposit.my_taxi.repository.UserTypeRepository;
import com.exposit.my_taxi.service.user.UserTypeService;
import com.exposit.my_taxi.service.user.dto.UserTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserTypeServiceImpl implements UserTypeService {
    private UserTypeRepository userTypeRepository;

    @Autowired
    public UserTypeServiceImpl(UserTypeRepository userRepository) {
        this.userTypeRepository = userRepository;
    }


    @Override
    public List<UserTypeDto> findAll() {
        return userTypeRepository.findAll().stream()
                .map(UserTypeDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserTypeDto> findById(Long id) {
        return Optional.ofNullable(userTypeRepository.findUserTypeEntityById(id))
                .map(UserTypeDto::new);
    }
}
