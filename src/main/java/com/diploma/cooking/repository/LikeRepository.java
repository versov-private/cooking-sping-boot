package com.diploma.cooking.repository;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.Like;
import com.diploma.cooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("select count(l) from Like as l where l.dish = ?1")
    Long findQuantityByDish(Dish dish);
    Like findByDishAndUser(Dish dish, User user);
    Boolean existsByDishAndUser(Dish dish, User user);
}
