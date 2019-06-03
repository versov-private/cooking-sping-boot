package com.diploma.cooking.service;

import com.diploma.cooking.model.*;

import java.util.List;
import java.util.Optional;

public interface DishService {

    List<Dish> findAll();
    Optional<Dish> findById(Long id);
    Dish saveOrUpdate(Dish dish);
    void delete(Dish dish);
    List<Dish> findDishesByProduct(Product product);
    List<Dish> findFavouriteDishesByUser(User user);
    List<Dish> findByStorage(Storage storage);
    List<Dish> findByUser(User user);
    List<Dish> findByTimeOfCooking(float timeOfCooking);
    List<Dish> findByLikeUser(User user);
    Dish findByComment(Comment comment);
}
