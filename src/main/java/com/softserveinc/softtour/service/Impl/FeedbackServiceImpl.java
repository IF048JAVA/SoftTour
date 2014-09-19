package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.repository.FeedbackDao;
import com.softserveinc.softtour.entity.Feedback;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.service.FeedbackService;

import java.util.List;

public class FeedbackServiceImpl implements FeedbackService{
    private FeedbackDao feedbackDao;
    public void setFeedbackDao (FeedbackDao feedbackDao) {
        this.feedbackDao=feedbackDao;
    }
    public void save (int cleanliness, int comfort, int location, int valueForMoney, String comment, Hotel hotel,
    User user) {
        feedbackDao.save(cleanliness,comfort,location,valueForMoney,comment,hotel,user);
    }
    public void update (long id, int cleanliness, int comfort, int location, int valueForMoney, String comment, Hotel
            hotel, User user) {
        feedbackDao.update(id, cleanliness, comfort, location, valueForMoney, comment, hotel, user);
    }
    public void delete (long id) {
        feedbackDao.delete(id);
    }
    public Feedback findById (long id) {
        return feedbackDao.findById(id);
    }
    public List<Feedback> getAll () {
        return feedbackDao.getAll();
    }
    public List<Feedback> findByUser (User user) {
        return feedbackDao.findByUser(user);
    }
}
