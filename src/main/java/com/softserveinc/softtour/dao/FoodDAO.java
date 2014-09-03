package com.softserveinc.softtour.dao;

import com.softserveinc.softtour.entity.Food;

import java.util.List;

public interface FoodDAO {
    public void save(String name);
    public void update(long id, String name);
    public void delete(long id);
    public Food findById(long id);
    public List<Food> findByName(String name);
    public List<Food> getAll();
}
