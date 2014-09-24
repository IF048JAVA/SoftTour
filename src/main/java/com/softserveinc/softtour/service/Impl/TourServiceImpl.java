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

    public void setTourRepository(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public List<Tour> findAll() {
        return tourRepository.findAll();
    }
}
