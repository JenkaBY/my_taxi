package com.exposit.my_taxi.service.signup.impl;

import com.exposit.my_taxi.model.user.UserEntity;
import com.exposit.my_taxi.model.user.UserStatus;
import com.exposit.my_taxi.model.user.UserType;
import com.exposit.my_taxi.repository.UserRepository;
import com.exposit.my_taxi.service.conversion.converter.UserEntityToUserDtoConverter;
import com.exposit.my_taxi.service.conversion.converter.UserStatusDtoToEntityConverter;
import com.exposit.my_taxi.service.conversion.converter.UserTypeDtoToEntityConverter;
import com.exposit.my_taxi.service.exception.ValidationException;
import com.exposit.my_taxi.service.security.PasswordEncoder;
import com.exposit.my_taxi.service.signup.SignupService;
import com.exposit.my_taxi.service.signup.dto.RegisteredUserDto;
import com.exposit.my_taxi.service.signup.dto.SignupDto;
import com.exposit.my_taxi.service.user.UserProfileService;
import com.exposit.my_taxi.service.user.UserService;
import com.exposit.my_taxi.service.user.UserStatusService;
import com.exposit.my_taxi.service.user.UserTypeService;
import com.exposit.my_taxi.service.user.dto.UserDto;
import com.exposit.my_taxi.service.user.dto.UserStatusDto;
import com.exposit.my_taxi.service.user.dto.UserTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImpl implements SignupService {
    private UserService userService;
    private UserProfileService userProfileService;
    private UserTypeService userTypeService;
    private PasswordEncoder passwordEncoder;
    private UserStatusService userStatusService;
    private UserRepository userRepository;

    private UserEntityToUserDtoConverter userToDtoConverter;
    private UserTypeDtoToEntityConverter userTypeDtoToEntity;
    private UserStatusDtoToEntityConverter statusDtoToEntityConverter;

    @Autowired
    public SignupServiceImpl(UserService userService, UserProfileService userProfileService,
                             UserTypeService userTypeService, PasswordEncoder passwordEncoder,
                             UserStatusService userStatusService, UserRepository userRepository,
                             UserEntityToUserDtoConverter userToDtoConverter,
                             UserTypeDtoToEntityConverter userTypeDtoToEntity,
                             UserStatusDtoToEntityConverter statusDtoToEntityConverter) {
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.userTypeService = userTypeService;
        this.passwordEncoder = passwordEncoder;
        this.userStatusService = userStatusService;
        this.userRepository = userRepository;
        this.userToDtoConverter = userToDtoConverter;
        this.userTypeDtoToEntity = userTypeDtoToEntity;
        this.statusDtoToEntityConverter = statusDtoToEntityConverter;
    }

    @Override
    public UserDto signUp(SignupDto credential) throws ValidationException {

        RegisteredUserDto user = new RegisteredUserDto();
        user.setUserStatus(UserStatus.PENDING_CONFIRM);
        user.setLogin(credential.getLogin());
        user.setUserType(UserType.CUSTOMER);
        user.setRawPassword(credential.getRawPassword());

        return userService.createNewUser(user);
    }

//    @Override
//    public UserDto registerNewUser(SignupDto credential, UserProfileDto profile, UserTypeDto userType) {
//        UserStatusDto status = userStatusService.findByLookupCode(UserStatus.CONFIRMED.getLookupCode());
//        UserEntity newUser = initUserEntity(credential, userType, status);
//
//        UserProfileDto profileDto = new UserProfileDto();
//
//        return userService.createNewUser(userToDtoConverter.convert(newUser));
//    }

    private UserEntity initUserEntity(SignupDto credential, UserTypeDto userType, UserStatusDto userStatus) {
        UserEntity newUser = new UserEntity();
        newUser.setEmail(credential.getLogin());
        newUser.setHashPassword(passwordEncoder.encode(credential.getRawPassword()));

        newUser.setUserTypeEntity(userTypeDtoToEntity.convert(userType));

        newUser.setUserStatusEntity(statusDtoToEntityConverter.convert(userStatus));

        return newUser;
    }

    private UserEntity initUserEntityDefault(SignupDto credential) {
        UserTypeDto userType = userTypeService.findByLookupCode(UserType.CUSTOMER.getLookupCode());
        UserStatusDto status = userStatusService.findByLookupCode(UserStatus.CONFIRMED.getLookupCode());

        return initUserEntity(credential, userType, status);
    }
}
