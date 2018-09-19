package com.exposit.my_taxi.service.signup;

import com.exposit.my_taxi.service.exception.ValidationException;
import com.exposit.my_taxi.service.signup.dto.SignupDto;
import com.exposit.my_taxi.service.user.dto.UserDto;

public interface SignupService {
    UserDto signUp(SignupDto credential) throws ValidationException;

//    UserDto registerNewUser(SignupDto credential, ProfileDto profile, UserTypeDto userType);
}
