package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.repository.TourRepository;
import com.softserveinc.softtour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 */
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;

    @Override
    public List<Tour> findByHotelAndDaysAndPrice(String country, int days, BigDecimal price) {
        return tourRepository.findByHotelAndDaysAndPrice(country, days, price);
    }

    @Override
    public List<Tour> findByCustomParameters(String country, Date date, int days, String departureCity,
                                             Date departureTime, BigDecimal price, String hotel, String food) {
        return tourRepository.findByCustomParameters(country, date, days, departureCity,
                                                     departureTime, price, hotel, food);
    }
}
