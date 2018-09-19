package com.exposit.my_taxi.service.security;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
    public String encode(String password) {
        return password;
    }

    public boolean matches(CharSequence pass, String password) {
        return true;
    }
}
