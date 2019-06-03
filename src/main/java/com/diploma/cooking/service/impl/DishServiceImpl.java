package com.diploma.cooking.service.impl;

import com.diploma.cooking.model.*;
import com.diploma.cooking.repository.DishRepository;
import com.diploma.cooking.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return dishRepository.findById(id);
    }

    @Override
    public Dish saveOrUpdate(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public void delete(Dish dish) {
        dishRepository.delete(dish);
    }

    @Override
    public List<Dish> findDishesByProduct(Product product) {
        return dishRepository.findDishesByProduct(product);
    }

    @Override
    public List<Dish> findFavouriteDishesByUser(User user) {
        return dishRepository.findFavouriteDishesByUser(user);
    }

    @Override
    public List<Dish> findByStorage(Storage storage) {
        return dishRepository.findByStorage(storage);
    }

    @Override
    public List<Dish> findByUser(User user) {
        return dishRepository.findByUser(user);
    }

    @Override
    public List<Dish> findByTimeOfCooking(float timeOfCooking) {
        return dishRepository.findByTimeOfCooking(timeOfCooking);
    }

    @Override
    public List<Dish> findByLikeUser(User user) {
        return dishRepository.findByLikeUser(user);
    }

    @Override
    public Dish findByComment(Comment comment) {
        return dishRepository.findByComment(comment);
    }

}
