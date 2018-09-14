package com.exposit.my_taxi.service.user.impl;

import com.exposit.my_taxi.repository.UserStatusRepository;
import com.exposit.my_taxi.service.conversion.converter.UserStatusEntityToDtoConverter;
import com.exposit.my_taxi.service.user.UserStatusService;
import com.exposit.my_taxi.service.user.dto.UserStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserStatusServiceImpl implements UserStatusService {
    private UserStatusRepository userStatusRepository;
    private UserStatusEntityToDtoConverter userStatusEntityToDtoConverter;

    @Autowired
    public UserStatusServiceImpl(UserStatusRepository userStatusRepository
            , UserStatusEntityToDtoConverter userStatusEntityToDtoConverter) {
        this.userStatusRepository = userStatusRepository;
        this.userStatusEntityToDtoConverter = userStatusEntityToDtoConverter;
    }


    @Override
    public List<UserStatusDto> findAll() {
        return userStatusRepository.findAll().stream()
                .map(UserStatusDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserStatusDto> findById(Long id) {
        return Optional.ofNullable(userStatusRepository.findUserStatusEntityById(id))
                .map(UserStatusDto::new);
    }

    @Override
    @Cacheable("user_statuses")
    public UserStatusDto findByLookupCode(String lookupCode) {
        return userStatusEntityToDtoConverter.convert(userStatusRepository.findUserStatusEntityByLookupCode(lookupCode));
    }
}
