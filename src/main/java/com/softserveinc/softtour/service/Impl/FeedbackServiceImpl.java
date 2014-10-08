package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.entity.Feedback;
import com.softserveinc.softtour.repository.FeedbackRepository;
import com.softserveinc.softtour.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback save(Feedback feedback) {
        return feedbackRepository.saveAndFlush(feedback);
    }

    @Override
    public Feedback findOne(long id) {
        return feedbackRepository.findOne(id);
    }

    @Override
    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public void delete(Feedback feedback) {
        feedbackRepository.delete(feedback);
    }
}
