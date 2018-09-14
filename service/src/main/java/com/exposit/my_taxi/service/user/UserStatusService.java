package com.exposit.my_taxi.service.user;

import com.exposit.my_taxi.service.user.dto.UserStatusDto;

import java.util.List;
import java.util.Optional;


public interface UserStatusService {
    List<UserStatusDto> findAll();

    Optional<UserStatusDto> findById(Long id);

    UserStatusDto findByLookupCode(String lookupCode);
}
