package com.softserveinc.softtour.dao;

import com.softserveinc.softtour.entity.Food;

import java.awt.List;

public interface FoodDAO {
    public void saveFood(String name);
    public void updateFood(long id, String name);
    public void deleteFood(long id);
    public Food findById(long id);
    public List<Food> findByName(String name);
    public List<Food> getAll();
}
