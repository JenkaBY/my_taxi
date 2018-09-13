package com.exposit.my_taxi.service.user.dto;

import com.exposit.my_taxi.model.user.UserEntity;

import java.util.Objects;

public class UserDtoOld {
    private String name;
    private Integer age;
    private Long id;

    public UserDtoOld(String name, Integer age, Long id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public UserDtoOld(String name) {
        this.name = name;
    }

    public UserDtoOld() {
    }

    public UserDtoOld(UserEntity userEntity) {
        this.name = userEntity.getNameOLD();
        this.age = userEntity.getAgeOLD();
        this.id = userEntity.getId();
    }

    public UserEntity convertToUser() {
        if (Objects.nonNull(name)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setNameOLD(name);
            userEntity.setAgeOLD(age);
            if (Objects.nonNull(id)) {
                userEntity.setId(id);
            }
            return userEntity;
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
