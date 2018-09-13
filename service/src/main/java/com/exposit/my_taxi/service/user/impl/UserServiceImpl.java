package com.exposit.my_taxi.service.user.impl;

import com.exposit.my_taxi.model.user.UserEntity;
import com.exposit.my_taxi.repository.UserRepository;
import com.exposit.my_taxi.service.user.UserService;
import com.exposit.my_taxi.service.user.dto.UserDtoOld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDtoOld> findAll() {
        return userRepository.findAll().stream()
                .map(UserDtoOld::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDtoOld> findById(Long id) {
        UserEntity foundUserEntity = userRepository.getOne(id);
        return Optional.ofNullable(Objects.nonNull(foundUserEntity) ? new UserDtoOld(foundUserEntity) : null);
    }

    @Override
    public Optional<UserDtoOld> findByName(String login) {
        UserEntity foundUserEntity = userRepository.findUserByLogin(login);
        return Optional.ofNullable(Objects.nonNull(foundUserEntity) ? new UserDtoOld(foundUserEntity) : null);
    }

    @Override
    public Optional<UserDtoOld> createNewUser(UserDtoOld user) {
        if (isUserLoginExist(user.getName())) {
            return Optional.empty();
        }
        UserEntity convertedUserEntity = user.convertToUser();
        UserDtoOld createdUserDtoOld = new UserDtoOld(userRepository.save(convertedUserEntity));
        return Optional.ofNullable(createdUserDtoOld);
    }

    @Override
    public UserDtoOld updateUser(UserDtoOld user) {
        UserEntity updatedUserEntity = userRepository.save(user.convertToUser());
        return new UserDtoOld(updatedUserEntity);
    }

    /**
     * Check if user exists with passed name
     *
     * @param login checked name
     * @return true if user exists, false otherwise
     */
    @Override
    public boolean isUserLoginExist(String login) {
        UserEntity foundUserEntity = userRepository.findUserByLogin(login);
        return Objects.nonNull(foundUserEntity);
    }
}
