package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.Region;

import java.util.List;

public interface RegionDao {

    public void save (String name, Country country);
    public void update (long id, String name, Country country);
    public void delete (long id);
    public Region findById (long id);
    public List<Region> findByName (String name);
    public List<Region> findByCountry (Country country);
    public List<Region> getAll();

}
