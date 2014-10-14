package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.parsers.impl.TyrComUaParser;
import com.softserveinc.softtour.repository.TourRepository;
import com.softserveinc.softtour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;
    @Override
    public List<Tour> parse(){
        TyrComUaParser parser = new TyrComUaParser("Туреччина", 3, 1, 500, 500);
        //List<Tour> resultList = parser.parse();
        return new ArrayList<Tour>();
        //return resultList;
    }
    @Override
    public Tour save(Tour tour) {
        return tourRepository.saveAndFlush(tour);
    }

    @Override
    public void delete(Tour tour) {
        tourRepository.delete(tour);
    }

    @Override
    public void deleteById(long id) {
        tourRepository.delete(id);
    }

    @Override
    public Tour findOne(long id) {
        return tourRepository.findOne(id);
    }

    @Override
    public List<Tour> findAll() {
        return tourRepository.findAll();
    }

    @Override
    public List<Tour> findByCountryAndPrice(String country, Integer minPrice, Integer maxPrice) {
        return tourRepository.findByCountryAndPrice(country, BigDecimal.valueOf(minPrice), BigDecimal.valueOf(maxPrice));
    }

    @Override
    public List<Tour> findByCountryAndDaysAndPrice(String country, int days, BigDecimal price) {
        return tourRepository.findByCountryAndDaysAndPrice(country, days, price);
    }

    @Override
    public List<Tour> findByCustomParameters(String country, Date date, int days, String departureCity,
                                             Time departureTime, BigDecimal price, String hotel, String food) {
        return tourRepository.findByCustomParameters(country, date, days, departureCity,
                                                     departureTime, price, hotel, food);
    }


}

