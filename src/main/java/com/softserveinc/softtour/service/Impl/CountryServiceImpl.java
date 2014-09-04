package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.dao.CountryDao;
import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.service.CountryService;

import java.util.List;

public class CountryServiceImpl implements CountryService {

    private CountryDao countryDao;

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public void save(String name) {
        this.countryDao.save(name);
    }

    @Override
    public void update(long id, String name) {
        this.countryDao.update(id, name);
    }

    @Override
    public void delete(long id) {
        this.countryDao.delete(id);
    }

    @Override
    public Country findById(long id) {
        return this.countryDao.findById(id);
    }

    @Override
    public List<Country> findByName(String name) {
        return this.countryDao.findByName(name);
    }

    @Override
    public List<Country> getAll() {
        return this.countryDao.getAll();
    }
}
