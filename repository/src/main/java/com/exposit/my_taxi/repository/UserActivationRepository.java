package com.exposit.my_taxi.repository;

import com.exposit.my_taxi.model.user.UserActivationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivationRepository extends JpaRepository<UserActivationEntity, Long> {
    UserActivationEntity findByActivationCode(String activationCode);
}
