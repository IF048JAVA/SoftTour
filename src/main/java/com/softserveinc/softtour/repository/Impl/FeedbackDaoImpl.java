package com.softserveinc.softtour.repository.Impl;

import com.softserveinc.softtour.repository.FeedbackDao;
import com.softserveinc.softtour.entity.Feedback;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class FeedbackDaoImpl extends HibernateDaoSupport implements FeedbackDao {
    @Override
    public void save (int cleanliness, int comfort, int location, int valueForMoney, String comment, Hotel hotel,
                      User user) {
        Feedback feedback = new Feedback(cleanliness, comfort, location, valueForMoney, comment, hotel, user);
        getHibernateTemplate().save(feedback);
    }
    @Override
    public void update (long id, int cleanliness, int comfort, int location, int valueForMoney, String comment, Hotel
            hotel, User user) {
        Feedback feedback = (Feedback)getHibernateTemplate().get(Feedback.class,id);
        if (feedback != null){
            feedback.setCleanliness(cleanliness);
            feedback.setComfort(comfort);
            feedback.setComment(comment);
            feedback.setHotel(hotel);
            feedback.setLocation(location);
            feedback.setUser(user);
            feedback.setValueForMoney(valueForMoney);
            getHibernateTemplate().update(feedback);
        } else
            System.err.println("Error!!!");
    }
    @Override
    public void delete (long id) {
        Feedback feedback = (Feedback)getHibernateTemplate().get(Feedback.class,id);
        if (feedback != null)
            getHibernateTemplate().delete(feedback);
        else
            System.err.println("Nothing to delete!!!");
    }
    @Override
    public Feedback findById (long id) {
        Feedback feedback = (Feedback) getHibernateTemplate().get(Feedback.class, id);
        return feedback;
    }
    @Override
    public List<Feedback> findByUser (User user) {
        List<Feedback> list = (List<Feedback>) getHibernateTemplate().find("FROM Feedback WHERE id = ?", user.getId());
        return list;
    }
    @Override
    public List<Feedback> getAll () {
        List<Feedback> list = (List<Feedback>) getHibernateTemplate().find("FROM Feedback");
        return list;
    }






}
