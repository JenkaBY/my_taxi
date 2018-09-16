package com.exposit.my_taxi.repository;

import com.exposit.my_taxi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDetailRepository extends JpaRepository<User, Long> {
}
