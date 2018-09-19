package com.exposit.my_taxi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Api(description = "Test controller to allow is Taxi Service available")
public class PingController {

    @GetMapping
    @ApiOperation(value = "Return 'Hello' string", notes = "And Nothing more")
    @ApiResponse(code = 200, message = "Always OK", response = String.class)
    public String ping() {
        return "Hello";
    }
}
