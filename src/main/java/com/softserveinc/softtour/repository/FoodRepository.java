package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Food;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FoodRepository extends JpaRepository<Food, Long> {
    public Food findByName(String name);
}
