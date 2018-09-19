package com.exposit.my_taxi.service.user;

import com.exposit.my_taxi.model.profile.ProfileEntity;
import com.exposit.my_taxi.service.user.dto.ProfileDto;

public interface ProfileService {
    ProfileEntity createNew(ProfileDto profileEntity);
}
