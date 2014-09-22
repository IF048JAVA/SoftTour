package com.softserveinc.softtour.service;

import com.softserveinc.softtour.entity.Country;

import java.util.List;

public interface CountryService {

    public void save(Country country);
    public void delete(long id);
    public Country findById(long id);
    public List<Country> findByName(String name);
    public List<Country> findAll();
}
