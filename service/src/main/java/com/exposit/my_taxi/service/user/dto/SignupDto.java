package com.exposit.my_taxi.service.user.dto;

public class SignupDto {
    private String login;
    private String rawPassword;

    public SignupDto() {
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
