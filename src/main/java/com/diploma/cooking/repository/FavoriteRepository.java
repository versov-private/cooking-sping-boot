package com.diploma.cooking.repository;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.Favorite;
import com.diploma.cooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUser(User user);
    List<Favorite> findByDish(Dish dish);
}
