package com.diploma.cooking.service;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    public Optional<Dish> findById(Long id) {
        return dishRepository.findById(id);
    }

    public void saveOrUpdate(Dish dish) {
        dishRepository.save(dish);
    }

    public void delete(Dish dish) {
        dishRepository.delete(dish);
    }

}
