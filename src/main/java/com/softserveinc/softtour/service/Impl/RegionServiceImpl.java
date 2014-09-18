package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.dao.RegionDao;
import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.Region;
import com.softserveinc.softtour.service.RegionService;

import java.util.List;

public class RegionServiceImpl implements RegionService {
    private RegionDao regionDao;
    public void setRegionDao (RegionDao regionDao) {
        this.regionDao=regionDao;
    }
    public void save (String name, Country country) {
        regionDao.save(name, country);
    }
    public void update (long id, String name, Country country) {
        regionDao.update(id, name, country);
    }
    public void delete (long id) {
        regionDao.delete(id);
    }
    public Region findById (long id) {
        return regionDao.findById(id);
    }
    public List<Region> findByName (String name) {
        return regionDao.findByName(name);
    }
    public List<Region> findByCountry (Country country) {
        return regionDao.findByCountry(country);
    }
    public List<Region> getAll() {
        return regionDao.getAll();
    }

}
