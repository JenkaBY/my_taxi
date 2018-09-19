package com.exposit.my_taxi.model.user;

import com.exposit.my_taxi.model.profile.ProfileEntity;
import com.exposit.my_taxi.model.shared.AbstractVersionEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class UserEntity extends AbstractVersionEntity {
    private static final long serialVersionUID = -1405846521960830912L;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "hash_password", nullable = false, length = 60)
    private String hashPassword;

    @Column(name = "password_created_at", nullable = false)
    private Timestamp passwordCreatedAt;

    @Column(name = "password_updated_at", nullable = false)
    private Timestamp passwordUpdatedAt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_type_id")
    private UserTypeEntity userTypeEntity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_status_id")
    private UserStatusEntity userStatus;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private ProfileEntity profile;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Timestamp getPasswordCreatedAt() {
        return passwordCreatedAt;
    }

    public void setPasswordCreatedAt(Timestamp passwordCreatedAt) {
        this.passwordCreatedAt = passwordCreatedAt;
    }

    public Timestamp getPasswordUpdatedAt() {
        return passwordUpdatedAt;
    }

    public void setPasswordUpdatedAt(Timestamp passwordUpdatedAt) {
        this.passwordUpdatedAt = passwordUpdatedAt;
    }

    public UserTypeEntity getUserTypeEntity() {
        return userTypeEntity;
    }

    public void setUserTypeEntity(UserTypeEntity userTypeEntity) {
        this.userTypeEntity = userTypeEntity;
    }

    public UserStatusEntity getUserStatusEntity() {
        return userStatus;
    }

    public void setUserStatusEntity(UserStatusEntity userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "UserEntity id:" + getId();
    }
}
