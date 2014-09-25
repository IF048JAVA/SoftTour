package com.softserveinc.softtour.service;

import com.softserveinc.softtour.entity.Tour;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TourService {
    List<Tour> findByHotelAndDaysAndPrice(String country, int days, BigDecimal price);
    List<Tour> findByCustomParameters(String country, Date date, int days, String departureCity, Date departureTime,
                                      BigDecimal price, String hotel, String food);
}
