package com.diploma.cooking.repository;

import com.diploma.cooking.model.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeStepRepository extends JpaRepository<RecipeStep, Long> {
}
