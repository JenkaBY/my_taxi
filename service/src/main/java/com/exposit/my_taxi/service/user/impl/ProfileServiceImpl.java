package com.exposit.my_taxi.service.user.impl;

import com.exposit.my_taxi.model.profile.ProfileEntity;
import com.exposit.my_taxi.repository.ProfileRepository;
import com.exposit.my_taxi.service.user.ProfileService;
import com.exposit.my_taxi.service.user.dto.ProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileServiceImpl implements ProfileService {
    private ProfileRepository profileRepository;
    private Converter<ProfileDto, ProfileEntity> profileDtoToEntityConverter;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, Converter<ProfileDto, ProfileEntity> profileDtoToEntityConverter) {
        this.profileRepository = profileRepository;
        this.profileDtoToEntityConverter = profileDtoToEntityConverter;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public ProfileEntity createNew(ProfileDto profileDto) {
        ProfileEntity profile = profileDtoToEntityConverter.convert(profileDto);
        profile = profileRepository.saveAndFlush(profile);
        return profile;
    }
}
