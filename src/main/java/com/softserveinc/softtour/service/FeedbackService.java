package com.softserveinc.softtour.service;


import com.softserveinc.softtour.entity.Feedback;
import java.util.List;

public interface FeedbackService {

    Feedback save(Feedback feedback);

    Feedback findOne(long id);

    List<Feedback> findAll();

    void delete(Feedback feedback);

    void deleteById(long id);

    List<Feedback> findByCustomParameters();

}
