package com.exposit.my_taxi.service.signup.dto;

public class SignupDto {
    private String email;
    private String rawPassword;
    private SignupProfileDto profile;

    public SignupDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public SignupProfileDto getProfile() {
        return profile;
    }

    public void setProfile(SignupProfileDto profile) {
        this.profile = profile;
    }
}
