package com.exposit.my_taxi.service.signup.dto;

import com.exposit.my_taxi.model.user.UserStatus;
import com.exposit.my_taxi.model.user.UserType;

public class RegisteredUserDto {
    private String login;
    private String rawPassword;
    private UserType userType;
    private UserStatus userStatus;

    public RegisteredUserDto() {
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }
}
