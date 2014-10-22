package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.repository.HotelRepository;
import com.softserveinc.softtour.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel save(Hotel hotel) {
        return hotelRepository.saveAndFlush(hotel);
    }

    @Override
    public Hotel findOne(long id) {
        return hotelRepository.findOne(id);
    }

    @Override
    public Page<Hotel> findByName(String name, Pageable pageable) {
        return hotelRepository.findByName(name, pageable);
    }

    @Override
    public Hotel findByName(String name) {
        return hotelRepository.findByName(name);
    }


    @Override
    public Page<Hotel> findByCustomParameters(List<String> country, BigDecimal rating, BigDecimal comfort, BigDecimal cleanliness,
                                              BigDecimal location, BigDecimal valueForMoney, Pageable pageable) {

        return hotelRepository.findByCustomParameters(country, rating, comfort, cleanliness,
                location, valueForMoney, pageable);
    }

    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public void setZero (Hotel hotel) {
        hotel.setCleanliness(BigDecimal.valueOf(0));
        hotel.setComfort(BigDecimal.valueOf(0));
        hotel.setRating(BigDecimal.valueOf(0));
        hotel.setLocation(BigDecimal.valueOf(0));
        hotel.setValueForMoney(BigDecimal.valueOf(0));
    }
}
