package com.exposit.my_taxi.model.user;

import com.exposit.my_taxi.model.shared.AbstractSystemEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "user_statuses")
@Entity
public class UserStatusEntity extends AbstractSystemEntity {

    @Column(name = "lookup_code", unique = true, nullable = false, length = 100)
    private String lookupCode;

    @Column(name = "title", unique = true, nullable = false, length = 100)
    private String title;

    public String getLookupCode() {
        return lookupCode;
    }

    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
