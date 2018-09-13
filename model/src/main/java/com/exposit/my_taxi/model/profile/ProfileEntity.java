package com.exposit.my_taxi.model.profile;

import com.exposit.my_taxi.model.shared.AbstractVersionEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "profiles")
public class ProfileEntity extends AbstractVersionEntity {

    @Column(name = "first_name", nullable = false, length = 500)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 500)
    private String lastName;

    @Column(name = "email", nullable = false, length = 254)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

//    private ImageEntity image;

}
