package com.exposit.my_taxi.repository;

import com.exposit.my_taxi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //    User findById(Long id);
    User findByLogin(String login);
}
