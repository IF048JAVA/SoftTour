package com.softserveinc.softtour.dao.Impl;

import com.softserveinc.softtour.dao.TourDao;
import com.softserveinc.softtour.entity.Food;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Tour;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 * Поки-що роблю тільки один findBy, решту закину пізніше
 */
public class TourDaoImpl extends HibernateDaoSupport implements TourDao {

    @Override
    public void save(java.util.Date date, int days, String departureCity, java.util.Date departureTime, BigDecimal price, Hotel hotel, Food food) {
        Tour tour = new Tour(date, days, departureCity, departureTime, price, hotel, food);
        getHibernateTemplate().save(tour);
    }

    @Override
    public void update(long id, java.util.Date date, int days, String departureCity, java.util.Date departureTime, BigDecimal price, Hotel hotel, Food food) {
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
    public List<Tour> findByDate(java.util.Date date) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        List<Tour> list = (List<Tour>) getHibernateTemplate().find("FROM Tour WHERE Tour.date = ?", sqlDate);
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tour> findByDays(int days) {
        List<Tour> list = (List<Tour>) getHibernateTemplate().find("FROM Tour WHERE days = ?", days);
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tour> findByDepartureCity(String departureCity) {
        List<Tour> list = (List<Tour>) getHibernateTemplate().find("FROM Tour WHERE departureCity = ?", departureCity);
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tour> findByDepartureTime(java.util.Date departureTime) {
        Time sqlTime = new java.sql.Time(departureTime.getTime());
        List<Tour> list = (List<Tour>) getHibernateTemplate().find("FROM Tour WHERE departureTime = ?", sqlTime);
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tour> findByPrice(BigDecimal price) {
        List<Tour> list = (List<Tour>) getHibernateTemplate().find("FROM Tour WHERE price = ?", price);
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tour> findByHotel(Hotel hotel) {
        List<Tour> list = (List<Tour>) getHibernateTemplate().find("FROM Tour WHERE hotel_id = ?", hotel.getId());
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tour> findByFood(Food food) {
        List<Tour> list = (List<Tour>) getHibernateTemplate().find("FROM Tour WHERE food_id = ?", food.getId());
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tour> getAll() {
        List<Tour> list = (List<Tour>) getHibernateTemplate().find("FROM Tour");
        return list;
    }
}
