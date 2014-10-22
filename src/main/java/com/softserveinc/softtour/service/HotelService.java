package com.softserveinc.softtour.service;

import com.softserveinc.softtour.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface HotelService {

    Hotel save(Hotel hotel);
    Hotel findOne(long id);
    Page <Hotel> findByName(String name, Pageable pageable);
    Page<Hotel> findByCustomParameters(List<String> country, BigDecimal rating, BigDecimal comfort, BigDecimal cleanliness,
                                       BigDecimal location, BigDecimal valueForMoney, Pageable pageable);
    List<Hotel> findAll();
    Hotel findByName(String name);
    void setZero(Hotel hotel);
}
