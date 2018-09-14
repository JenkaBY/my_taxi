package com.exposit.my_taxi.service.user;

import com.exposit.my_taxi.service.user.dto.UserTypeDto;

import java.util.List;
import java.util.Optional;

public interface UserTypeService {
    List<UserTypeDto> findAll();

    Optional<UserTypeDto> findById(Long id);

    UserTypeDto findByLookupCode(String lookupCode);
}
