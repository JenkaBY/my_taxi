package com.exposit.my_taxi.controller;

import com.exposit.my_taxi.service.user.UserService;
import com.exposit.my_taxi.service.user.dto.UserDto;
import io.swagger.annotations.*;
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

    @GetMapping("/all")
    @ApiOperation(value = "Get all existing users",
            notes = "All users will be here. If no users found, returns empty array")
    @ApiResponses({
            @ApiResponse(code = 200, message = "In any case we will get OK", response = UserDto.class, responseContainer = "List")
    })
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping
    @ApiOperation(value = "Get user by passed name",
            notes = "Try to ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "If user was found", response = UserDto.class),
            @ApiResponse(code = 400, message = "If user wasn't found")
    })
    public ResponseEntity<?> findByName(@ApiParam(value = "Passed name", required = true, example = "Test name") @RequestParam String name) {
        final Optional<UserDto> user = userService.findByName(name);
        return getResponseForUser(user);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {

        final Optional<UserDto> user = userService.findById(id);
        return getResponseForUser(user);
    }

    @PostMapping
    @ApiOperation(value = "Creates a new user",
            notes = "Creates a new User by passed following json \"{\"name\":\"New User name\", \"age\": 21 }\" where 'name' is required parameter.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "If user was created", response = UserDto.class),
            @ApiResponse(code = 400, message = "If user wasn't created")
    })
    public ResponseEntity<?> createUser(@ApiParam(value = "Passed name", required = true, example = "{\"name\":\"New User name\", \"age\": 21 }") @RequestBody UserDto user) {
        final Optional<UserDto> createdUser = userService.createNewUser(user);
        return getResponseForUser(createdUser);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody UserDto user) {

        UserDto updatedUserDto = userService.updateUser(user);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    private ResponseEntity<?> getResponseForUser(Optional<UserDto> user) {
        return user.map(UserDto -> new ResponseEntity<>(UserDto, HttpStatus.CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
