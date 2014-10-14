package com.softserveinc.softtour.service;


import com.softserveinc.softtour.entity.Feedback;
import com.softserveinc.softtour.entity.Hotel;

import java.util.List;

public interface FeedbackService {

    Feedback save(Feedback feedback);
    Feedback findOne(long id);
    List<Feedback> findAll();
    void delete(Feedback feedback);
    List<Feedback> findByHotelId(Long hotelId);
}
