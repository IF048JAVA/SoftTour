package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.repository.CountryRepository;
import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation= Propagation.SUPPORTS, readOnly = true)
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Country save(Country country) {
        return this.countryRepository.save(country);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void delete(long id) {
        this.countryRepository.delete(id);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Country findById(long id) {
        return this.countryRepository.findOne(id);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Country findByName(String name) {
        return countryRepository.findByName(name);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }



}

