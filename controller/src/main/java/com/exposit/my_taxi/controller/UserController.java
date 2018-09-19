package com.exposit.my_taxi.controller;

import com.exposit.my_taxi.service.user.UserService;
import com.exposit.my_taxi.service.user.dto.ResultDto;
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

    @GetMapping("/activate")
    public ResponseEntity<?> activate(@RequestParam String code) {
        ResultDto resultDto;
        try {
            userService.activateUserByCode(code);
            resultDto = new ResultDto(true);
        } catch (Exception ep) {
            resultDto = new ResultDto(false);
        }
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.ok(new ResultDto(true));
    }

    private ResponseEntity<?> getResponseForUser(Optional<UserDto> user) {
        return user.map(UserDto -> new ResponseEntity<>(UserDto, HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
