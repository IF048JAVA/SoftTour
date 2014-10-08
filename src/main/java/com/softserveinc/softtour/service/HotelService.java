package com.softserveinc.softtour.service;

import com.softserveinc.softtour.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HotelService {

    Hotel save(Hotel hotel);
    Hotel findOne(long id);
    Page <Hotel> findByName(String name, Pageable pageable);
    void deleteById(long id);
    Page<Hotel> findByCustomParameters(List<String> country, Integer rating, Integer comfort, Integer cleanliness,
                                       Integer location, Integer valueForMoney, Pageable pageable);
}
