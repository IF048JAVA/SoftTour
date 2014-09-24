package com.softserveinc.softtour.service;

import com.softserveinc.softtour.entity.Tour;

import java.math.BigDecimal;
import java.util.List;

public interface TourService {
    Tour save(Tour tour);

    Tour findOne(long id);

    List<Tour> findAll();

    void delete(Tour tour);

    void deleteById(long id);

    List<Tour> findByCustomParameters(String country,BigDecimal minPrice,BigDecimal maxPrice);
}
