package com.diploma.cooking.service;

import com.diploma.cooking.model.Role;
import com.diploma.cooking.model.addition.RoleName;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> findAll();
    Optional<Role> findById(Long id);
    Role saveOrUpdate(Role role);
    void delete(Role role);
    Optional<Role> findByName(RoleName roleName);
}
