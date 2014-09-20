package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.repository.HotelRepository;
import com.softserveinc.softtour.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public void delete(Hotel hotel) {
        hotelRepository.delete(hotel);

    }

    @Override
    public void deleteById(long id) {
        hotelRepository.delete(id);

    }

    @Override
    public List<Hotel> findByCustomParameters() {
        return null;
    }
}
