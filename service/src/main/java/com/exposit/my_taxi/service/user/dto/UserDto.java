package com.exposit.my_taxi.service.user.dto;

import com.exposit.my_taxi.model.user.UserEntity;
import com.exposit.my_taxi.model.user.UserStatus;
import com.exposit.my_taxi.model.user.UserType;

public class UserDto {
    private String login;
    private UserType userType;
    private UserStatus userStatus;

    public UserDto() {
    }

    public UserDto(UserEntity userEntity) {
        this.setLogin(userEntity.getLogin());
        this.setUserStatus(UserStatus.getFromLookupCode(userEntity.getUserStatusEntity().getLookupCode()));
        this.setUserType(UserType.getFromLookupCode(userEntity.getUserTypeEntity().getLookupCode()));
    }

    public UserEntity convertToEntity() {
        return null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
}
