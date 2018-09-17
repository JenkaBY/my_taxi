package com.exposit.my_taxi.service.security;

import com.exposit.my_taxi.model.Person;
import com.exposit.my_taxi.model.Role;
import com.exposit.my_taxi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String ROLE = "ROLE_";

    private PersonRepository personRepository;

    @Autowired
    public UserDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person user = personRepository.findByLogin(login);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getHashPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                convertToGrantAuthorities(user.getRoles())
        );
    }

    private Set<GrantedAuthority> convertToGrantAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getTitle())
                .map(String::toUpperCase)
                .map(role -> ROLE + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}