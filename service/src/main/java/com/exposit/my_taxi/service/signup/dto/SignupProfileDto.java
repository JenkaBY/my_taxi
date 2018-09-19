package com.exposit.my_taxi.service.signup.dto;

public class SignupProfileDto {
    private String firstName;
    private String lastName;

    public SignupProfileDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
