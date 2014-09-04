package com.softserveinc.softtour.service;

import com.softserveinc.softtour.entity.Food;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Tour;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 */
public interface TourService {
    public void save(Date date, int days, String departureCity,
                     Date departureTime, BigDecimal price, Hotel hotel, Food food);
    public void update(long id, Date date, int days, String departureCity,
                       Date departureTime, BigDecimal price, Hotel hotel, Food food);
    public void delete(long id);
    public Tour findById(long id);
    public List<Tour> findByDate(Date dateFrom, Date dateTo);
    public List<Tour> findByDays(int daysFrom, int DaysTo);
    public List<Tour> findByDepartureCity(String...departureCity);
    public List<Tour> findByDepartureTime(Date departureTimeFrom, Date departureTimeTo);
    public List<Tour> findByPrice(BigDecimal priceFrom, BigDecimal priceTo);
    public List<Tour> findByHotel(Hotel...hotel);
    public List<Tour> findByFood(Food...food);
    public List<Tour> getAll();
}
