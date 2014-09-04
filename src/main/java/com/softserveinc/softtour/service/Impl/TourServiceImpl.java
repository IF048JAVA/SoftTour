package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.dao.TourDao;
import com.softserveinc.softtour.entity.Food;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.service.TourService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 */
public class TourServiceImpl implements TourService {
    private TourDao tourDao;

    @Override
    public void save(Date date, int days, String departureCity, Date departureTime, BigDecimal price, Hotel hotel, Food food) {
        tourDao.save(date, days, departureCity, departureTime, price, hotel, food);
    }

    @Override
    public void update(long id, Date date, int days, String departureCity, Date departureTime, BigDecimal price, Hotel hotel, Food food) {
        tourDao.update(id, date, days, departureCity, departureTime, price, hotel, food);
    }

    @Override
    public void delete(long id) {
        tourDao.delete(id);
    }

    @Override
    public Tour findById(long id) {
        return tourDao.findById(id);
    }

    @Override
    public List<Tour> findByDate(Date date) {
        return tourDao.findByDate(date);
    }

    @Override
    public List<Tour> findByDays(int days) {
        return tourDao.findByDays(days);
    }

    @Override
    public List<Tour> findByDepartureCity(String departureCity) {
        return tourDao.findByDepartureCity(departureCity);
    }

    @Override
    public List<Tour> findByDepartureTime(Date departureTime) {
        return tourDao.findByDepartureTime(departureTime);
    }

    @Override
    public List<Tour> findByPrice(BigDecimal price) {
        return tourDao.findByPrice(price);
    }

    @Override
    public List<Tour> findByHotel(Hotel hotel) {
        return tourDao.findByHotel(hotel);
    }

    @Override
    public List<Tour> findByFood(Food food) {
        return tourDao.findByFood(food);
    }

    @Override
    public List<Tour> getAll() {
        return tourDao.getAll();
    }
}
