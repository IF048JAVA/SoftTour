package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.entity.Food;
import com.softserveinc.softtour.repository.FoodRepository;
import com.softserveinc.softtour.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public void save(Food food){foodRepository.save(food);}

    @Override
    public void delete(long id){foodRepository.delete(id);}

    @Override
    public Food findById(long id){return foodRepository.findOne(id);}

    @Override
    public Food findByName(String name){return foodRepository.findByName(name); }

    @Override
    public List<Food> getAll(){return foodRepository.findAll();}
}
