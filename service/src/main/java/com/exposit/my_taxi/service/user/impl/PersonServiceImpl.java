package com.exposit.my_taxi.service.user.impl;

import com.exposit.my_taxi.model.Role;
import com.exposit.my_taxi.model.RoleE;
import com.exposit.my_taxi.model.User;
import com.exposit.my_taxi.repository.UserRepository;
import com.exposit.my_taxi.service.user.PersonService;
import com.exposit.my_taxi.service.user.RoleService;
import com.exposit.my_taxi.service.user.dto.SignupDto;
import com.exposit.my_taxi.service.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PersonServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto findById(Long id) {
        return UserDto.create(userRepository.findById(id).orElse(null));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = find(userDto.getId());
        user.setLogin(userDto.getLogin());
        Set<Role> roles = userDto.getRoles().stream()
                .map(RoleE::name)
                .map(name -> roleService.findByTitle(name))
                .collect(Collectors.toSet());
        user.setRoles(roles);
        return UserDto.create(userRepository.save(user));
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::create)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createCustomer(SignupDto credential) {
        User user = create(credential, RoleE.CUSTOMER);
        return UserDto.create(userRepository.save(user));
    }

    @Override
    public UserDto createAdmin(SignupDto credential) {
        User user = create(credential, RoleE.ADMIN);
        return UserDto.create(userRepository.save(user));
    }

    private User create(SignupDto credential, RoleE role) {
        User user = new User();
        user.setLogin(credential.getLogin());
        user.setHashPassword(passwordEncoder.encode(credential.getRawPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByTitle(role.name()));
        user.setRoles(roles);
        return user;
    }

    private User find(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
