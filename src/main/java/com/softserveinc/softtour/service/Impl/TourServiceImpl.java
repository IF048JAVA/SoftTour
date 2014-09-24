package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.repository.TourRepository;
import com.softserveinc.softtour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 */
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
    public List<Tour> findByCustomParameters() {
        return null;
    }
}
