package com.exposit.my_taxi.service.user.dto;

import com.exposit.my_taxi.model.user.UserStatusEntity;

public class UserStatusDto {
    private Long id;
    private String title;
    private String lookupCode;

    public UserStatusDto() {
    }

    public UserStatusDto(UserStatusEntity status) {
        this.id = status.getId();
        this.title = status.getTitle();
        this.lookupCode = status.getLookupCode();
    }

    public UserStatusEntity convertToEntity() {
        UserStatusEntity status = new UserStatusEntity();
        status.setLookupCode(this.getLookupCode());
        status.setTitle(this.getTitle());
        return status;
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
