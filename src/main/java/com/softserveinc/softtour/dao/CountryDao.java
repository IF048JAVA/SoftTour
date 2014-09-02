package com.softserveinc.softtour.dao;

import java.util.List;

public interface CountryDao {

    void save(String name);
    void update(long id, String name);
    void delete(long id);
    CountryDao findById(long id);
    List<CountryDao> findByName(String name);
    List<CountryDao> getAll();
}
