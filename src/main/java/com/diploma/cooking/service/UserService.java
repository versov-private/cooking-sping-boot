package com.diploma.cooking.service;

import com.diploma.cooking.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    Optional<User> findById(Long id);
    User saveOrUpdate(User user);
    void delete(User user);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
