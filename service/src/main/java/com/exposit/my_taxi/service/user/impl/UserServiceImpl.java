package com.exposit.my_taxi.service.user.impl;

import com.exposit.my_taxi.model.user.UserEntity;
import com.exposit.my_taxi.repository.UserRepository;
import com.exposit.my_taxi.service.conversion.converter.RegisteredUserDtoToUserEntityConverter;
import com.exposit.my_taxi.service.conversion.converter.UserEntityToUserDtoConverter;
import com.exposit.my_taxi.service.exception.ValidationException;
import com.exposit.my_taxi.service.security.PasswordEncoder;
import com.exposit.my_taxi.service.signup.dto.RegisteredUserDto;
import com.exposit.my_taxi.service.user.UserService;
import com.exposit.my_taxi.service.user.dto.UserDto;
import com.exposit.my_taxi.service.user.validation.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RegisteredUserDtoToUserEntityConverter registeredUserDtoToUserEntityConverter;
    private UserEntityToUserDtoConverter userToDto;
    private LoginValidator loginValidator;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RegisteredUserDtoToUserEntityConverter registeredUserDtoToUserEntityConverter,
                           UserEntityToUserDtoConverter userToDto, LoginValidator loginValidator, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.registeredUserDtoToUserEntityConverter = registeredUserDtoToUserEntityConverter;
        this.userToDto = userToDto;
        this.loginValidator = loginValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userToDto::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        UserEntity foundUserEntity = userRepository.getOne(id);
        return Optional.ofNullable(Objects.nonNull(foundUserEntity) ? userToDto.convert(foundUserEntity) : null);
    }

    @Override
    public Optional<UserDto> findByLogin(String login) {
        UserEntity foundUserEntity = userRepository.findByEmail(login);
        return Optional.ofNullable(Objects.nonNull(foundUserEntity) ? userToDto.convert(foundUserEntity) : null);
    }


    @Override
    public UserDto createNewUser(RegisteredUserDto user) throws ValidationException {
        validateLogin(user.getLogin());
        UserEntity newUser = registeredUserDtoToUserEntityConverter.convert(user);
        newUser.setHashPassword(passwordEncoder.encode(user.getRawPassword()));
        Timestamp time = Timestamp.from(Instant.now());
        newUser.setPasswordCreatedAt(time);
        newUser.setPasswordUpdatedAt(time);
        newUser = userRepository.saveAndFlush(newUser);
        return userToDto.convert(newUser);
    }

    /**
     * Check if user exists with passed login
     *
     * @param login checked login
     * @return true if user exists, false otherwise
     */
    @Override
    public boolean isUserExist(String login) {
        UserEntity foundUserEntity = userRepository.findByEmail(login);
        return Objects.nonNull(foundUserEntity);
    }

    private void validateLogin(String login) throws ValidationException {
        loginValidator.validate(login);
    }
}
