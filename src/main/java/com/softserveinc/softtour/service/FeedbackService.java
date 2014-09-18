package com.softserveinc.softtour.service;


import com.softserveinc.softtour.entity.Feedback;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.User;
import java.util.List;

public interface FeedbackService {
    public void save (int cleanliness, int comfort, int location, int valueForMoney, String comment, Hotel hotel,
            User user);
    public void update (long id, int cleanliness, int comfort, int location, int valueForMoney, String comment, Hotel
            hotel, User user);
    public void delete (long id);
    public Feedback findById (long id);
    public List<Feedback> getAll ();
    public List<Feedback> findByUser (User user);
}
