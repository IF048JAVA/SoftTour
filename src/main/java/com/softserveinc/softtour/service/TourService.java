package com.softserveinc.softtour.service;

import com.softserveinc.softtour.entity.Tour;

import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 */
public interface TourService {
    List<Tour> findAll();
}
