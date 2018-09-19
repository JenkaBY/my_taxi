package com.exposit.my_taxi.service.user.dto;

import com.exposit.my_taxi.model.user.UserStatus;
import com.exposit.my_taxi.model.user.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto {
    private Long id;
    private String login;
    private UserType userType;
    private UserStatus userStatus;
    @JsonIgnore
    private String hashPassword;

    public UserDto() {
    }

//    public UserDto(UserEntity userEntity) {
//        this.setId(userEntity.getId());
//        this.setEmail(userEntity.getEmail());
//        this.setUserStatus(UserStatus.getFromLookupCode(userEntity.getUserStatusEntity().getLookupCode()));
//        this.setUserType(UserType.getFromLookupCode(userEntity.getUserTypeEntity().getLookupCode()));
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }
}
