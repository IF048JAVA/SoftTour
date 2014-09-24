package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.repository.TourRepository;
import com.softserveinc.softtour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 */
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;

    public void setTourRepository(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }
}
