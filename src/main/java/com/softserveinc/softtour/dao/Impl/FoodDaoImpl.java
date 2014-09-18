package com.softserveinc.softtour.dao.Impl;

import com.softserveinc.softtour.dao.FoodDao;
import com.softserveinc.softtour.entity.Food;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import java.util.List;

public class FoodDaoImpl extends HibernateDaoSupport implements FoodDao {
    @Override
    public void save(String name){
        Food food = new Food(name);
        getHibernateTemplate().save(food);
    }
    @Override
    public void update(long id, String name){
        Food food = (Food) getHibernateTemplate().get(Food.class, id);
        if (food != null) {
            food.setName(name);
            getHibernateTemplate().update(food);
        } else {
            System.err.println("Error! \n you don't have food");
        }
    }
    @Override
    public void delete(long id){
        Food food = (Food) getHibernateTemplate().get(Food.class, id);
        if (food != null) {
            getHibernateTemplate().delete(food);
        } else {
            System.err.println("Error! \n you don't have food");
        }
    }
    @Override
    public Food findById(long id) {
        Food food = (Food) getHibernateTemplate().get(Food.class, id);
        return food;
    }
    @Override
    public List<Food> findByName(String name) {
        List<Food> list = (List<Food>) getHibernateTemplate().find("FROM Food WHERE name = ?", name);
        return list;
    }
    @Override
    public List<Food> getAll(){
        List<Food> list = (List<Food>) getHibernateTemplate().find("FROM Food");
        return list;
    }


}
