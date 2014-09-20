package com.softserveinc.softtour.service;

import com.softserveinc.softtour.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel save(Hotel hotel);
    Hotel findOne(long id);
    List<Hotel> findAll();
    void delete(Hotel hotel);
    void deleteById(long id);
    List<Hotel> findByCustomParameters();

}
