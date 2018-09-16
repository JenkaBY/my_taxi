package com.exposit.my_taxi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "person_details")
@JsonIgnoreProperties(value = {"new"})
public class PersonDetail implements Serializable {
    private static final long serialVersionUID = -140584652196083091L;

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500, unique = true)
    private String name;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User id:" + getId();
    }
}
