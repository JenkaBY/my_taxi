package com.exposit.my_taxi.service.user;

import com.exposit.my_taxi.model.Role;

public interface RoleService {

    Role findByTitle(String title);
}
