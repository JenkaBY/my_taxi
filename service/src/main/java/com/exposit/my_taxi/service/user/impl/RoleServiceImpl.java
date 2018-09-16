package com.exposit.my_taxi.service.user.impl;

import com.exposit.my_taxi.model.Role;
import com.exposit.my_taxi.repository.RoleRepository;
import com.exposit.my_taxi.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByTitle(String title) {
        return roleRepository.findByTitle(capitalyze(title));

    }

    private String capitalyze(String word) {
        String first = word.substring(0, 1);
        return word.toLowerCase().replaceFirst(first.toLowerCase(), first.toUpperCase());
    }
}
