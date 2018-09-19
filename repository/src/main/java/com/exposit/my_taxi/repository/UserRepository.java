package com.exposit.my_taxi.repository;

import com.exposit.my_taxi.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String login);

    UserEntity findUserEntityById(Long id);
}
