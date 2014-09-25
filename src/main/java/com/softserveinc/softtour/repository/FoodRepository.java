package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Food;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
    public Food findByName(String name);
}
