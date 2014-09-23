package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.entity.Region;
import com.softserveinc.softtour.repository.RegionRepository;
import com.softserveinc.softtour.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public Region save(Region region) {
        return regionRepository.saveAndFlush(region);
    }

    @Override
    public Region findOne(long id) {
        return regionRepository.findOne(id);
    }

    @Override
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    @Override
    public void delete(Region region) {
        regionRepository.delete(region);
    }

    @Override
    public void deleteById(long id) {
        regionRepository.delete(id);
    }

    @Override
    public List<Region> findByCustomParameters() {
        return null;
    }
}


