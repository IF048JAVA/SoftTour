package com.softserveinc.softtour.service;

import com.softserveinc.softtour.entity.Hotel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HotelService {

    Hotel save(Hotel hotel);
    Hotel findOne(long id);
    List <Hotel> findByName(String name);
    List<Hotel> findAll(Pageable pageable);
    void deleteById(long id);
    List<Hotel> findByCustomParameters(List<String> country, Integer rating, Integer comfort, Integer cleanliness,
                                       Integer location, Integer valueForMoney, Pageable pageable);
}
