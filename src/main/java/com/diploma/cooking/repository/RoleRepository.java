package com.diploma.cooking.repository;

import com.diploma.cooking.model.Role;
import com.diploma.cooking.model.addition.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
