package com.softserveinc.softtour.service;


import com.softserveinc.softtour.entity.Region;

import java.util.List;

public interface RegionService {

    Region save(Region region);

    Region findOne(long id);

    List<Region> findAll();

    void delete(Region region);

    void deleteById(long id);

    List<Region> findByCustomParameters();
}
