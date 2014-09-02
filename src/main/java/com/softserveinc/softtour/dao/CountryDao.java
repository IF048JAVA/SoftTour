package com.softserveinc.softtour.dao;

import com.softserveinc.softtour.entity.Country;

import java.util.List;

public interface CountryDao {

    void save(String name);
    void update(long id, String name);
    void delete(long id);
    CountryDao findById(long id);
    List<Country> findByName(String name);
    List<Country> getAll();
}
