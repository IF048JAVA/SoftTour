package com.softserveinc.softtour.service;

import com.softserveinc.softtour.entity.Country;

import java.util.List;

public interface CountryService {

    public Country save(Country country);
    public void delete(long id);
    public Country findById(long id);
    public Country findByName(String name);
    public List<Country> findAll();
}
