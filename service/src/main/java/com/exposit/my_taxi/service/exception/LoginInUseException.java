package com.exposit.my_taxi.service.exception;

public class LoginInUseException extends ValidationException {
    public LoginInUseException(String login) {
        super("Login '" + login + "' is used by another user. Choose another one.");
    }
}
