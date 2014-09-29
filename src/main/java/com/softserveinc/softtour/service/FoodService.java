package com.softserveinc.softtour.service;


import com.softserveinc.softtour.entity.Food;

import java.util.List;

public interface FoodService {
    public void save(Food food);
    public void delete(long id);
    public Food findById(long id);
    public Food findByName(String name);
    public List<Food> getAll();
}
