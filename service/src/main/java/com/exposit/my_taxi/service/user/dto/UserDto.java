package com.exposit.my_taxi.service.user.dto;

import com.exposit.my_taxi.model.User;

import java.util.Objects;

public class UserDto {
    private String name;
    private Integer age;
    private Long id;

    public UserDto(String name, Integer age, Long id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public UserDto(String name) {
        this.name = name;
    }

    public UserDto() {
    }

    public UserDto(User user) {
        this.name = user.getName();
        this.age = user.getAge();
        this.id = user.getId();
    }

    public User convertToUser() {
        if (Objects.nonNull(name)) {
            User user = new User();
            user.setName(name);
            user.setAge(age);
            if (Objects.nonNull(id)) {
                user.setId(id);
            }
            return user;
        }
        throw new RuntimeException("Name can't be blank!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
