package com.exposit.my_taxi.service.user.impl;

import com.exposit.my_taxi.model.profile.ProfileEntity;
import com.exposit.my_taxi.model.user.UserActivationEntity;
import com.exposit.my_taxi.model.user.UserEntity;
import com.exposit.my_taxi.model.user.UserStatus;
import com.exposit.my_taxi.model.user.UserStatusEntity;
import com.exposit.my_taxi.repository.UserRepository;
import com.exposit.my_taxi.service.conversion.converter.RegisterUserDtoToUserEntityConverter;
import com.exposit.my_taxi.service.conversion.converter.UserEntityToUserDtoConverter;
import com.exposit.my_taxi.service.conversion.converter.UserStatusToEntityConverter;
import com.exposit.my_taxi.service.exception.ValidationException;
import com.exposit.my_taxi.service.security.PasswordEncoder;
import com.exposit.my_taxi.service.signup.dto.RegisterUserDto;
import com.exposit.my_taxi.service.user.ProfileService;
import com.exposit.my_taxi.service.user.UserActivationService;
import com.exposit.my_taxi.service.user.UserService;
import com.exposit.my_taxi.service.user.dto.UserDto;
import com.exposit.my_taxi.service.user.validation.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ProfileService profileService;
    private RegisterUserDtoToUserEntityConverter registerUserDtoToUserEntityConverter;
    private UserEntityToUserDtoConverter userToDto;
    private LoginValidator loginValidator;
    private PasswordEncoder passwordEncoder;
    private UserActivationService userActivationService;
    private UserStatusToEntityConverter userStatusToEntityConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RegisterUserDtoToUserEntityConverter registerUserDtoToUserEntityConverter,
                           UserEntityToUserDtoConverter userToDto, LoginValidator loginValidator, PasswordEncoder passwordEncoder,
                           ProfileService profileService, UserActivationService userActivationService,
                           UserStatusToEntityConverter userStatusToEntityConverter) {
        this.userRepository = userRepository;
        this.registerUserDtoToUserEntityConverter = registerUserDtoToUserEntityConverter;
        this.userToDto = userToDto;
        this.loginValidator = loginValidator;
        this.passwordEncoder = passwordEncoder;
        this.profileService = profileService;
        this.userActivationService = userActivationService;
        this.userStatusToEntityConverter = userStatusToEntityConverter;
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
    public Optional<UserDto> findByEmail(String email) {
        UserEntity foundUserEntity = userRepository.findByEmail(email);
        return Optional.ofNullable(Objects.nonNull(foundUserEntity) ? userToDto.convert(foundUserEntity) : null);
    }

    @Override
    @Transactional
    public UserDto createNewUser(RegisterUserDto user) throws ValidationException {
        validateLogin(user.getEmail());
        UserEntity newUser = registerUserDtoToUserEntityConverter.convert(user);
        newUser.setHashPassword(passwordEncoder.encode(user.getRawPassword()));

        Timestamp time = Timestamp.from(Instant.now());
        newUser.setPasswordCreatedAt(time);
        newUser.setPasswordUpdatedAt(time);

        ProfileEntity profile = profileService.createNew(user.getProfileDto());
        newUser.setProfile(profile);

        newUser = userRepository.saveAndFlush(newUser);

        String activationCode = userActivationService.createNewForUser(newUser);
        System.out.println(activationCode);

        return userToDto.convert(newUser);
    }


    @Override
    @Transactional
    public void activateUserByCode(String code) {
        UserActivationEntity activation = userActivationService.findByCode(code);
        UserEntity user = activation.getUser();
        UserStatusEntity statusConfirmed = userStatusToEntityConverter.convert(UserStatus.CONFIRMED);
        user.setUserStatus(statusConfirmed);
        userRepository.saveAndFlush(user);

        userActivationService.delete(activation);
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Check if user exists with passed email
     *
     * @param email checked email
     * @return true if user exists, false otherwise
     */
    @Override
    public boolean isUserExist(String email) {
        UserEntity foundUserEntity = userRepository.findByEmail(email);
        return Objects.nonNull(foundUserEntity);
    }

    private void validateLogin(String email) throws ValidationException {
        loginValidator.validate(email);
    }
}
