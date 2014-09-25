package com.softserveinc.softtour.service;

import com.softserveinc.softtour.entity.Tour;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TourService {
    Tour save(Tour tour);
    void delete(Tour tour);
    void deleteById(long id);
    Tour findOne(long id);
    List<Tour> findAll();
    List<Tour> findByCountryAndPrice(String country, Integer minPrice, Integer maxPrice);
    List<Tour> findByCountryAndDaysAndPrice(String country, int days, BigDecimal price);
    List<Tour> findByCustomParameters(String country, Date date, int days, String departureCity, Date departureTime,
                                      BigDecimal price, String hotel, String food);
}
