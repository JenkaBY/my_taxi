package com.exposit.my_taxi.model.user;

import com.exposit.my_taxi.model.shared.AbstractSystemEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "user_activations")
@Entity
public class UserActivationEntity extends AbstractSystemEntity {

    @Column(name = "activation_code", unique = true, nullable = false)
    private String activationCode;

    @Column(name = "createdAt")
    private Timestamp createdAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
