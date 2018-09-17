package com.exposit.my_taxi.service.user.impl;

import com.exposit.my_taxi.model.Person;
import com.exposit.my_taxi.model.Role;
import com.exposit.my_taxi.model.RoleE;
import com.exposit.my_taxi.repository.PersonRepository;
import com.exposit.my_taxi.service.user.PersonService;
import com.exposit.my_taxi.service.user.RoleService;
import com.exposit.my_taxi.service.user.dto.PersonDto;
import com.exposit.my_taxi.service.user.dto.SignupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PersonDto findById(Long id) {
        return PersonDto.create(personRepository.findById(id).orElse(null));
    }

    @Override
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public PersonDto update(PersonDto personDto) {
        Person person = find(personDto.getId());
        person.setLogin(personDto.getLogin());
        Set<Role> roles = personDto.getRoles().stream()
                .map(RoleE::name)
                .map(name -> roleService.findByTitle(name))
                .collect(Collectors.toSet());
        person.setRoles(roles);
        return PersonDto.create(personRepository.save(person));
    }

    @Override
    public List<PersonDto> findAll() {
        return personRepository.findAll().stream()
                .map(PersonDto::create)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto createCustomer(SignupDto credential) {
        Person person = create(credential, RoleE.CUSTOMER);
        return PersonDto.create(personRepository.save(person));
    }

    @Override
    public PersonDto createAdmin(SignupDto credential) {
        Person person = create(credential, RoleE.ADMIN);
        return PersonDto.create(personRepository.save(person));
    }

    private Person create(SignupDto credential, RoleE role) {
        Person person = new Person();
        person.setLogin(credential.getLogin());
        person.setHashPassword(credential.getRawPassword());
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByTitle(role.name()));
        person.setRoles(roles);
        return person;
    }

    private Person find(Long id) {
        return personRepository.findById(id).orElse(null);
    }
}
