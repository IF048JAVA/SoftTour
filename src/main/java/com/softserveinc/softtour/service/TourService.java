package com.softserveinc.softtour.service;

import com.softserveinc.softtour.entity.Tour;

import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 */
public interface TourService {
    Tour save(Tour tour);

    Tour findOne(long id);

    List<Tour> findAll();

    void delete(Tour tour);

    void deleteById(long id);

    List<Tour> findByCustomParameters();
}
