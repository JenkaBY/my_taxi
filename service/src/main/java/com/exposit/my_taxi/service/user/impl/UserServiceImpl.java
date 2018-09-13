package com.exposit.my_taxi.service.user.impl;

import com.exposit.my_taxi.model.user.UserEntity;
import com.exposit.my_taxi.repository.UserRepository;
import com.exposit.my_taxi.service.user.UserService;
import com.exposit.my_taxi.service.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        UserEntity foundUserEntity = userRepository.getOne(id);
        return Optional.ofNullable(Objects.nonNull(foundUserEntity) ? new UserDto(foundUserEntity) : null);
    }

    @Override
    public Optional<UserDto> findByName(String name) {
        UserEntity foundUserEntity = userRepository.findUserByName(name);
        return Optional.ofNullable(Objects.nonNull(foundUserEntity) ? new UserDto(foundUserEntity) : null);
    }

    @Override
    public Optional<UserDto> createNewUser(UserDto user) {
        if (isUserNameExist(user.getName())) {
            return Optional.empty();
        }
        UserEntity convertedUserEntity = user.convertToUser();
        UserDto createdUserDto = new UserDto(userRepository.save(convertedUserEntity));
        return Optional.ofNullable(createdUserDto);
    }

    @Override
    public UserDto updateUser(UserDto user) {
        UserEntity updatedUserEntity = userRepository.save(user.convertToUser());
        return new UserDto(updatedUserEntity);
    }

    /**
     * Check if user exists with passed name
     *
     * @param name checked name
     * @return true if user exists, false otherwise
     */
    @Override
    public boolean isUserNameExist(String name) {
        UserEntity foundUserEntity = userRepository.findUserByName(name);
        return Objects.nonNull(foundUserEntity);
    }
}
