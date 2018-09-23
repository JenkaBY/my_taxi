package com.exposit.my_taxi.service.user.dto;

import com.exposit.my_taxi.model.Role;
import com.exposit.my_taxi.model.RoleE;
import com.exposit.my_taxi.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDto {
    private Long id;
    private String login;
    private Set<RoleE> roles;

    public UserDto() {
    }

    public UserDto(Long id, String login, Set<String> roleTitle) {
        this.id = id;
        this.login = login;
        setRolesFromTitles(roleTitle);
    }

    public static UserDto create(Long id, String login, Set<String> roleTitle) {
        return new UserDto(id, login, roleTitle);
    }

    public static UserDto create(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.create(user.getId(),
                user.getLogin(),
                user.getRoles().stream()
                        .map(Role::getTitle)
//                        .map(String::toUpperCase)
//                        .map(RoleE::valueOf)
                        .collect(Collectors.toSet()))
                ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<RoleE> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleE> roles) {
        this.roles = roles;
    }

    public void setRolesFromTitles(Set<String> roleTitles) {
        this.roles = roleTitles.stream()
                .map(String::toUpperCase)
                .map(RoleE::valueOf)
                .collect(Collectors.toSet());
    }


}
