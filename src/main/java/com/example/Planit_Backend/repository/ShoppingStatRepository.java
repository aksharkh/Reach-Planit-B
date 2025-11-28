package com.example.Planit_Backend.repository;

import com.example.Planit_Backend.entity.ShoppingStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShoppingStatRepository extends JpaRepository<ShoppingStat, Long> {

    List<ShoppingStat> findByUserId(Long userId);
}
