package com.diploma.cooking.service;

import com.diploma.cooking.model.Favorite;

import java.util.List;
import java.util.Optional;

public interface FavoriteService {

    List<Favorite> findAll();
    Optional<Favorite> findById(Long id);
    Favorite saveOrUpdate(Favorite favorite);
    void delete(Favorite favorite);
}
