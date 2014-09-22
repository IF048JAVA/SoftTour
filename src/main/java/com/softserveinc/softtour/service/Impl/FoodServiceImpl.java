package com.softserveinc.softtour.service.Impl;


import com.softserveinc.softtour.entity.Food;
import com.softserveinc.softtour.repository.FoodRepository;
import com.softserveinc.softtour.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation= Propagation.REQUIRED, readOnly=true)
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;


    @Override
    public void save(Food food){
        foodRepository.save(food);
    }
    @Override
    @Transactional(readOnly = false)
    public void update(long id, Food food){foodRepository.saveAndFlush(food);}
    @Override
    public void delete(long id){foodRepository.delete(id);}
    @Override
    public Food findById(long id){return foodRepository.findOne(id);}

    @Override
    public List<Food> getAll(){return foodRepository.findAll();}
}
