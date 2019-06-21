package com.diploma.cooking.service;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.Favorite;
import com.diploma.cooking.model.User;

import java.util.List;
import java.util.Optional;

public interface FavoriteService {

    List<Favorite> findAll();
    Optional<Favorite> findById(Long id);
    Favorite saveOrUpdate(Favorite favorite);
    void delete(Favorite favorite);
    List<Favorite> findByUser(User user);
    List<Favorite> findByDish(Dish dish);
}
