package com.exposit.my_taxi.service.utils.impl;

import com.exposit.my_taxi.service.utils.ActivationCodeGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("simpleActivationCodeGenerator")
public class SimpleActivationCodeGenerator implements ActivationCodeGenerator {
    @Override
    public String generate() {
        return (new UUID(System.currentTimeMillis(), Long.MAX_VALUE - System.currentTimeMillis())).toString();
    }
}
