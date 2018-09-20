package com.exposit.my_taxi.service.user.impl;

import com.exposit.my_taxi.repository.UserTypeRepository;
import com.exposit.my_taxi.service.conversion.converter.UserTypeEntityToDtoConverter;
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
    private UserTypeEntityToDtoConverter userTypeEntityToDtoConverter;

    @Autowired
    public UserTypeServiceImpl(UserTypeRepository userTypeRepository,
                               UserTypeEntityToDtoConverter userTypeEntityToDtoConverter) {
        this.userTypeRepository = userTypeRepository;
        this.userTypeEntityToDtoConverter = userTypeEntityToDtoConverter;
    }

    @Override
    public List<UserTypeDto> findAll() {
        return userTypeRepository.findAll().stream()
                .map(UserTypeDto::new)
                .collect(Collectors.toList());
    }

    @Override
//    TODO Remove this method
    public Optional<UserTypeDto> findById(Long id) {
        return Optional.ofNullable(userTypeRepository.findUserTypeEntityById(id))
                .map(UserTypeDto::new);
    }

    @Override
    public UserTypeDto findByLookupCode(String lookupCode) {
        return userTypeEntityToDtoConverter.convert(userTypeRepository.findByLookupCode(lookupCode));
    }
}
