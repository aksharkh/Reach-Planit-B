package com.example.Planit_Backend.repository;

import com.example.Planit_Backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category , Long> {

    List<Category> findByIsFeatured(boolean isFeatured);

//    List<Category> findByUserId(Long userId);
}
