package com.exposit.my_taxi.controller;

import com.exposit.my_taxi.service.user.UserTypeService;
import com.exposit.my_taxi.service.user.dto.UserStatusDto;
import com.exposit.my_taxi.service.user.dto.UserTypeDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user_types")
@Api(value = "/user_types", description = "Methods allow to get information about all available types of users")
public class UserTypeController {
    private final UserTypeService userTypeService;

    @Autowired
    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @GetMapping
    @ApiOperation(value = "Get all user statuses",
            notes = "Return all available users statuses. If no user statuses found, returns empty array")
    @ApiResponses({
            @ApiResponse(code = 200, message = "In any case we will get OK", response = UserStatusDto.class, responseContainer = "List")
    })
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userTypeService.findAll());
    }

    @ApiOperation(value = "Get user by passed id",
            notes = "Try to find user status by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "If user type was found", response = UserTypeDto.class),
            @ApiResponse(code = 400, message = "If user type wasn't found")
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {

        final Optional<UserTypeDto> userType = userTypeService.findById(id);
        return getResponseForUser(userType);
    }

    private ResponseEntity<?> getResponseForUser(Optional<UserTypeDto> userType) {
        return userType.map(userTypeDto -> new ResponseEntity<>(userTypeDto, HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
