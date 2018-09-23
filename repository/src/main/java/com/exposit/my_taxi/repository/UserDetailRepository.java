package com.exposit.my_taxi.repository;

import com.exposit.my_taxi.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
}
