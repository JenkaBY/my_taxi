//package com.exposit.my_taxi.controller;
//
//import com.exposit.my_taxi.service.user.UserService;
//import com.exposit.my_taxi.service.user.dto.UserDtoOld;
//import io.swagger.annotations.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/users_old")
//@Api(value = "/users_old", description = "Methods to manage users and their data")
//public class UserControllerOld {
//    private final UserService userService;
//
//    @Autowired
//    public UserControllerOld(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/")
//    @ApiOperation(value = "Get all existing users",
//            notes = "All users will be here. If no users found, returns empty array")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "In any case we will get OK", response = UserDtoOld.class, responseContainer = "List")
//    })
//    public ResponseEntity<?> findAll() {
//        return ResponseEntity.ok(userService.findAll());
//    }
//
//    @GetMapping
//    @ApiOperation(value = "Get user by passed name",
//            notes = "Try to ")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "If user was found", response = UserDtoOld.class),
//            @ApiResponse(code = 400, message = "If user wasn't found")
//    })
//    public ResponseEntity<?> findByLogin(@ApiParam(value = "Passed name", required = true, example = "Test name") @RequestParam String name) {
//        final Optional<UserDtoOld> user = userService.findByLogin(name);
//        return getResponseForUser(user);
//    }
//
//    @GetMapping(path = "/{id}")
//    public ResponseEntity<?> findById(@PathVariable long id) {
//
//        final Optional<UserDtoOld> user = userService.findById(id);
//        return getResponseForUser(user);
//    }
//
//    @PostMapping
//    @ApiOperation(value = "Creates a new user",
//            notes = "Creates a new UserEntity by passed following json \"{\"name\":\"New UserEntity name\", \"age\": 21 }\" where 'name' is required parameter.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "If user was created", response = UserDtoOld.class),
//            @ApiResponse(code = 400, message = "If user wasn't created")
//    })
//    public ResponseEntity<?> createUser(@ApiParam(value = "Passed name", required = true, example = "{\"name\":\"New UserEntity name\", \"age\": 21 }") @RequestBody UserDtoOld user) {
//        final Optional<UserDtoOld> createdUser = userService.createNewUser(user);
//        return getResponseForUser(createdUser);
//    }
//
//    @PutMapping(path = "/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody UserDtoOld user) {
//
//        UserDtoOld updatedUserDtoOld = userService.updateUser(user);
//        return new ResponseEntity<>(updatedUserDtoOld, HttpStatus.OK);
//    }
//
//    private ResponseEntity<?> getResponseForUser(Optional<UserDtoOld> user) {
//        return user.map(UserDtoOld -> new ResponseEntity<>(UserDtoOld, HttpStatus.CREATED))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }
//}
