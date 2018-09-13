package com.exposit.my_taxi.controller;

import com.exposit.my_taxi.service.user.UserStatusService;
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
@RequestMapping("/user_statuses")
@Api(value = "/user_statuses", description = "Methods allow to get information about all available statuses of users")
public class UserStatusController {
    private final UserStatusService userStatusService;

    @Autowired
    public UserStatusController(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all user statuses",
            notes = "Return all available users statuses. If no user statuses found, returns empty array")
    @ApiResponses({
            @ApiResponse(code = 200, message = "In any case we will get OK", response = UserStatusDto.class, responseContainer = "List")
    })
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userStatusService.findAll());
    }

    @ApiOperation(value = "Get user status by passed id",
            notes = "Try to find user status by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "If user status was found", response = UserTypeDto.class),
            @ApiResponse(code = 400, message = "If user status wasn't found")
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {

        final Optional<UserStatusDto> userStatus = userStatusService.findById(id);
        return getResponseForUser(userStatus);
    }

    private ResponseEntity<?> getResponseForUser(Optional<UserStatusDto> userType) {
        return userType.map(userStatusDto -> new ResponseEntity<>(userStatusDto, HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
