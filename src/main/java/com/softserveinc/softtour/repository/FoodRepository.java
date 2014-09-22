package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Food;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @Modifying
    @Query("UPDATE Food SET name=?2 WHERE id=?1")
    public void update(long id, String name);

    public List<Food> findByIdOrName(long id, String name);
}
