package com.softserveinc.softtour.dao;

import com.softserveinc.softtour.entity.Food;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Tour;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 */
public interface TourDao {
    public void save(Date date, int days, String departureCity,
                     Date departureTime, BigDecimal price, Hotel hotel, Food food);
    public void update(long id, Date date, int days, String departureCity,
                       Date departureTime, BigDecimal price, Hotel hotel, Food food);
    public void delete(long id);
    public Tour findById(long id);
    public List<Tour> findByDate(Date date);
    public List<Tour> findByDays(int days);
    public List<Tour> findByDepartureCity(String departureCity);
    public List<Tour> findByDepartureTime(Date departureTime);
    public List<Tour> findByPrice(BigDecimal price);
    public List<Tour> findByHotel(Hotel hotel);
    public List<Tour> findByFood(Food food);
    public List<Tour> getAll();
}
