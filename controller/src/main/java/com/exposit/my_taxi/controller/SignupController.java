package com.exposit.my_taxi.controller;

import com.exposit.my_taxi.service.exception.ValidationException;
import com.exposit.my_taxi.service.signup.SignupService;
import com.exposit.my_taxi.service.signup.dto.SignupDto;
import com.exposit.my_taxi.service.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/signup")
public class SignupController {
    private SignupService signupService;

    @Autowired
    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> signup(@RequestBody SignupDto credential) throws ValidationException {
        UserDto createdUser = signupService.signUp(credential);

        return new ResponseEntity(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> all(@RequestParam(name = "num") Long id) {
        SignupDto credential = new SignupDto();
        credential.setLogin("Login" + id);
        credential.setRawPassword("sda");
        UserDto createdUser = null;
        try {
            createdUser = signupService.signUp(credential);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return Objects.nonNull(createdUser) ? ResponseEntity.ok(createdUser) : ResponseEntity.ok("NULL");
    }
}
