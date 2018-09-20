package com.exposit.my_taxi.repository;


import com.exposit.my_taxi.model.user.UserTypeEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserTypeEntity, Long> {
    UserTypeEntity findUserTypeEntityById(Long id);

    @Cacheable("user_types")
    UserTypeEntity findByLookupCode(String lookupCode);
}
