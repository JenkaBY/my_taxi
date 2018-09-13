package com.exposit.my_taxi.service.user.dto;

import com.exposit.my_taxi.model.user.UserTypeEntity;

public class UserTypeDto {
    private Long id;
    private String title;
    private String lookupCode;

    public UserTypeDto() {
    }

    public UserTypeDto(UserTypeEntity type) {
        this.id = type.getId();
        this.title = type.getTitle();
        this.lookupCode = type.getLookupCode();
    }

    public UserTypeEntity convertToEntity() {
        UserTypeEntity type = new UserTypeEntity();
        type.setLookupCode(this.getLookupCode());
        type.setTitle(this.getTitle());
        return type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLookupCode() {
        return lookupCode;
    }

    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }
}
