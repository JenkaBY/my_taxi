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
import com.exposit.my_taxi.service.signup.dto.RegisterUserDto;
import com.exposit.my_taxi.service.signup.dto.SignupDto;
import com.exposit.my_taxi.service.user.ProfileService;
import com.exposit.my_taxi.service.user.UserService;
import com.exposit.my_taxi.service.user.UserStatusService;
import com.exposit.my_taxi.service.user.UserTypeService;
import com.exposit.my_taxi.service.user.dto.ProfileDto;
import com.exposit.my_taxi.service.user.dto.UserDto;
import com.exposit.my_taxi.service.user.dto.UserStatusDto;
import com.exposit.my_taxi.service.user.dto.UserTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImpl implements SignupService {
    private UserService userService;
    private ProfileService profileService;
    private UserTypeService userTypeService;
    private PasswordEncoder passwordEncoder;
    private UserStatusService userStatusService;
    private UserRepository userRepository;

    private UserEntityToUserDtoConverter userToDtoConverter;
    private UserTypeDtoToEntityConverter userTypeDtoToEntity;
    private UserStatusDtoToEntityConverter statusDtoToEntityConverter;

    @Autowired
    public SignupServiceImpl(UserService userService, ProfileService profileService,
                             UserTypeService userTypeService, PasswordEncoder passwordEncoder,
                             UserStatusService userStatusService, UserRepository userRepository,
                             UserEntityToUserDtoConverter userToDtoConverter,
                             UserTypeDtoToEntityConverter userTypeDtoToEntity,
                             UserStatusDtoToEntityConverter statusDtoToEntityConverter) {
        this.userService = userService;
        this.profileService = profileService;
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

        RegisterUserDto user = new RegisterUserDto();
        user.setUserStatus(UserStatus.PENDING_CONFIRM);
        user.setEmail(credential.getEmail());
        user.setUserType(UserType.OPERATOR);
        user.setRawPassword(credential.getRawPassword());

        ProfileDto profileDto = new ProfileDto();
        profileDto.setFirstName(credential.getProfile().getFirstName());
        profileDto.setLastName(credential.getProfile().getLastName());

        user.setProfileDto(profileDto);

        return userService.createNewUser(user);
    }

//    @Override
//    public UserDto registerNewUser(SignupDto credential, ProfileDto profile, UserTypeDto userType) {
//        UserStatusDto status = userStatusService.findByLookupCode(UserStatus.CONFIRMED.getLookupCode());
//        UserEntity newUser = initUserEntity(credential, userType, status);
//
//        ProfileDto profileDto = new ProfileDto();
//
//        return userService.createNewUser(userToDtoConverter.convert(newUser));
//    }

    private UserEntity initUserEntity(SignupDto credential, UserTypeDto userType, UserStatusDto userStatus) {
        UserEntity newUser = new UserEntity();
        newUser.setEmail(credential.getEmail());
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
