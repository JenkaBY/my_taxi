package com.exposit.my_taxi.repository;

import com.exposit.my_taxi.model.profile.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

}
