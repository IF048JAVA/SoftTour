package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.repository.TourRepository;
import com.softserveinc.softtour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;

    @Override
    public Tour save(Tour tour) {
        return tourRepository.saveAndFlush(tour);
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
    public void delete(Tour tour) {
        tourRepository.delete(tour);
    }

    @Override
    public void deleteById(long id) {
        tourRepository.delete(id);
    }

    @Override
    public List<Tour> findByCustomParameters(String country, BigDecimal minPrice, BigDecimal maxPrice) {
        return tourRepository.findByCustomParameters(country,minPrice,maxPrice);
    }
}
