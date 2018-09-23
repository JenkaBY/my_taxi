package com.exposit.my_taxi.service.security;

import com.exposit.my_taxi.model.Role;
import com.exposit.my_taxi.model.User;
import com.exposit.my_taxi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String ROLE = "ROLE_";

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getHashPassword(), convertToGrantAuthorities(user.getRoles()));
    }

    private Set<GrantedAuthority> convertToGrantAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(Role::getTitle)
                .map(String::toUpperCase)
                .map(ROLE::concat)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}