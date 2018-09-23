package com.exposit.my_taxi.controller;

import com.exposit.my_taxi.service.user.PersonService;
import com.exposit.my_taxi.service.user.dto.SignupDto;
import com.exposit.my_taxi.service.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        UserDto person = personService.findById(id);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody SignupDto signupDto) {
        System.out.println(signupDto);
        return ResponseEntity.ok(personService.createCustomer(signupDto));
    }

    @PutMapping
    @RequestMapping("/{userId}")
    public ResponseEntity<?> updatePerson(@RequestBody UserDto user, @PathVariable Long userId) {
        UserDto updated = personService.update(user);
        return ResponseEntity.ok(updated);
    }
}
