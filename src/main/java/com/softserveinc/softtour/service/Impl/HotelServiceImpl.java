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
    public Page <Hotel> findByName(String name, Pageable pageable) {
        return hotelRepository.findByName(name, pageable);
    }

    @Override
    public void deleteById(long id) {
        hotelRepository.delete(id);
    }

    @Override
    public Page<Hotel> findByCustomParameters(List<String> country, Integer rating, Integer comfort,Integer cleanliness,
                                              Integer location, Integer valueForMoney, Pageable pageable) {

        return hotelRepository.findByCustomParameters(country, BigDecimal.valueOf(rating),
                BigDecimal.valueOf(comfort), BigDecimal.valueOf(cleanliness),
                BigDecimal.valueOf(location), BigDecimal.valueOf(valueForMoney), pageable);
    }

    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }
}
