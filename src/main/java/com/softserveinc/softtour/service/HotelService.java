package com.softserveinc.softtour.service;

import com.softserveinc.softtour.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel save(Hotel hotel);

    Hotel findOne(long id);

     List <Hotel> findByName(String name);

    List<Hotel> findAll();

    void deleteById(long id);

    List<Hotel> findByCustomParameters(String country, String region, Integer rating,
                                       Integer comfort, Integer cleanliness, Integer location, Integer valueForMoney);

}
