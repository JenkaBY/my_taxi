package com.exposit.my_taxi.controller;

import com.exposit.my_taxi.service.user.UserService;
import com.exposit.my_taxi.service.user.dto.UserDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@Api(value = "/users", description = "Methods to manage users and their data")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    public ResponseEntity<?> findByLogin(@RequestParam String name) {
//        final Optional<UserDto> user = userService.findByLogin(name);
        return null; //getResponseForUser(user);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {

        final Optional<UserDto> user = userService.findById(id);
        return getResponseForUser(user);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody UserDto user) {

//        UserDto updatedUserDto = userService.updateUser(user);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    private ResponseEntity<?> getResponseForUser(Optional<UserDto> user) {
        return user.map(UserDto -> new ResponseEntity<>(UserDto, HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
