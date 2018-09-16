package com.exposit.my_taxi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "people")
@JsonIgnoreProperties(value = {"new"})
public class Person implements Serializable {
    private static final long serialVersionUID = -140584652196083091L;

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500, unique = true)
    private String login;

    @Column(name = "hash_password", length = 150, nullable = false)
    private String hashPassword;

    @ManyToMany
    @JoinTable(
            name = "people_roles",
            joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "person")
    private PersonDetail detail;

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

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public PersonDetail getDetail() {
        return detail;
    }

    public void setDetail(PersonDetail detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Person id:" + getId();
    }
}
