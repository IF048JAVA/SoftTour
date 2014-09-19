package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Country;

import java.util.List;

public interface CountryDao {

    public void save(String name);
    public void update(long id, String name);
    public void delete(long id);
    public Country findById(long id);
    public List<Country> findByName(String name);
    public List<Country> getAll();
}
