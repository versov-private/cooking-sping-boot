package com.diploma.cooking.service;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.Like;

import java.util.List;
import java.util.Optional;

public interface LikeService {

    List<Like> findAll();
    Optional<Like> findById(Long id);
    Like saveOrUpdate(Like like);
    void delete(Like like);
    Long findQuantityByDish(Dish dish);
}
