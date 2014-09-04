package com.softserveinc.softtour.dao.Impl;

import com.softserveinc.softtour.dao.TourDao;
import com.softserveinc.softtour.entity.Food;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Tour;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 * Поки-що роблю тільки один findBy, решту закину пізніше
 */
public class TourDaoImpl extends HibernateDaoSupport implements TourDao {

    @Override
    public void save(Date date, int days, String departureCity, Date departureTime, BigDecimal price, Hotel hotel, Food food) {
        Tour tour = new Tour(date, days, departureCity, departureTime, price, hotel, food);
        getHibernateTemplate().save(tour);
    }

    @Override
    public void update(long id, Date date, int days, String departureCity, Date departureTime, BigDecimal price, Hotel hotel, Food food) {
        Tour tour = (Tour) getHibernateTemplate().get(Tour.class, id);
        if(tour != null) {
            tour.setDate(date);
            tour.setDays(days);
            tour.setDepartureCity(departureCity);
            tour.setDepartureTime(departureTime);
            tour.setPrice(price);
            tour.setHotel(hotel);
            tour.setFood(food);
            getHibernateTemplate().update(tour);
        } else
            System.err.println("Error!!!");
    }

    @Override
    public void delete(long id) {
        Tour tour = (Tour)getHibernateTemplate().get(Tour.class,id);
        if (tour != null)
            getHibernateTemplate().delete(tour);
        else
            System.err.println("Nothing to delete!!!");
    }

    @Override
    public Tour findById(long id) {
        Tour tour = (Tour) getHibernateTemplate().get(Tour.class, id);
        return tour;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tour> getAll() {
        List<Tour> list = (List<Tour>) getHibernateTemplate().find("FROM Tour");
        return list;
    }
}
