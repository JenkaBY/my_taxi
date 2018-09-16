package com.exposit.my_taxi.repository;

import com.exposit.my_taxi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByTitle(String title);
}
