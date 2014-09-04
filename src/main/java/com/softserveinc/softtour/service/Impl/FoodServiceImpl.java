package com.softserveinc.softtour.service.Impl;


import com.softserveinc.softtour.dao.FoodDao;
import com.softserveinc.softtour.entity.Food;
import com.softserveinc.softtour.service.FoodService;

import java.util.List;

public class FoodServiceImpl implements FoodService {
    private FoodDao foodDao;

    public void setFoodDao (FoodDao foodDao) {this.foodDao = foodDao;}
    @Override
    public void save(String name){foodDao.save(name);}
    @Override
    public void update(long id, String name){foodDao.update(id, name);}
    @Override
    public void delete(long id){foodDao.delete(id);}
    @Override
    public Food findById(long id){return foodDao.findById(id);}
    @Override
    public List<Food> findByName(String name){return foodDao.findByName(name);}
    @Override
    public List<Food> getAll(){return foodDao.getAll();}
}
