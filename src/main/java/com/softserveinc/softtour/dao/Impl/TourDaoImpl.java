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
    public List<Tour> findByDate(java.util.Date dateFrom, java.util.Date dateTo) {
        java.sql.Date sqlDateFrom = new java.sql.Date(dateFrom.getTime());
        java.sql.Date sqlDateTo = new java.sql.Date(dateTo.getTime());
        List<Tour> list = (List<Tour>) getHibernateTemplate().
                find("FROM Tour WHERE Tour.date >= ? AND Tour.date <= ? ", sqlDateFrom, sqlDateTo);
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tour> findByDays(int daysFrom, int daysTo) {
        List<Tour> list = (List<Tour>) getHibernateTemplate().
                find("FROM Tour WHERE days >= ? AND days <= ?", daysFrom, daysTo);
        return list;
    }

    @Override
    public List<Tour> findByDepartureCity(String...departureCity) {
        // create query
        StringBuilder stringQueryBuilder = new StringBuilder("FROM Tour WHERE");
        for(int i = 0; i < departureCity.length; i++){
            stringQueryBuilder.append(" departureCity = ? OR");
        }
        stringQueryBuilder.delete(stringQueryBuilder.length()-3, stringQueryBuilder.length()); //remove last OR
        String queryString = stringQueryBuilder.toString();

        List<Tour> list = (List<Tour>) getHibernateTemplate().find(queryString, departureCity);
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tour> findByDepartureTime(java.util.Date departureTimeFrom, java.util.Date departureTimeTo) {
        Time sqlTimeFrom = new Time(departureTimeFrom.getTime());
        Time sqlTimeTo = new Time(departureTimeTo.getTime());
        List<Tour> list = (List<Tour>) getHibernateTemplate().
                find("FROM Tour WHERE departureTime >= ? AND departureTime <= ?", departureTimeFrom, departureTimeTo);
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tour> findByPrice(BigDecimal priceFrom, BigDecimal priceTo) {
        List<Tour> list = (List<Tour>) getHibernateTemplate().
                find("FROM Tour WHERE priceFrom >= ? AND priceTo <= ?", priceFrom, priceTo);
        return list;
    }

    @Override
    public List<Tour> findByHotel(Hotel...hotel) {
        // create query
        StringBuilder stringQueryBuilder = new StringBuilder("FROM Hotel WHERE");
        for(int i = 0; i < hotel.length; i++){
            stringQueryBuilder.append(" hotel_id = ? OR");
        }
        stringQueryBuilder.delete(stringQueryBuilder.length()-3, stringQueryBuilder.length()); //remove last OR
        String queryString = stringQueryBuilder.toString();

        //create & initialize regions id array
        long[] hotelsId = new long[hotel.length];
        for(int i = 0; i < hotel.length; i++){
            hotelsId[i] = hotel[i].getId();
        }

        List<Tour> list = (List<Tour>) getHibernateTemplate().find(queryString, hotelsId);
        return list;
    }

    @Override
    public List<Tour> findByFood(Food...food) {
        // create query
        StringBuilder stringQueryBuilder = new StringBuilder("FROM Hotel WHERE");
        for(int i = 0; i < food.length; i++){
            stringQueryBuilder.append(" food_id = ? OR");
        }
        stringQueryBuilder.delete(stringQueryBuilder.length()-3, stringQueryBuilder.length()); //remove last OR
        String queryString = stringQueryBuilder.toString();

        //create & initialize regions id array
        long[] foodsId = new long[food.length];
        for(int i = 0; i < food.length; i++){
            foodsId[i] = food[i].getId();
        }

        List<Tour> list = (List<Tour>) getHibernateTemplate().find(queryString, foodsId);
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tour> getAll() {
        List<Tour> list = (List<Tour>) getHibernateTemplate().find("FROM Tour");
        return list;
    }
}
