package com.diploma.cooking.repository;

import com.diploma.cooking.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
