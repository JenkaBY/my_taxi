package com.exposit.my_taxi.repository;


import com.exposit.my_taxi.model.user.UserStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepository extends JpaRepository<UserStatusEntity, Long> {
    UserStatusEntity findUserStatusEntityById(Long id);
}
