package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository <Country, Long> {

    public List<Country> findByName(String name);

}
