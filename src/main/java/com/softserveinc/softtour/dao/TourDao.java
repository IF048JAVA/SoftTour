package com.softserveinc.softtour.dao;

import com.softserveinc.softtour.entity.Food;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Tour;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 * Поки-що роблю тільки один findBy, решту закину пізніше
 */
public interface TourDao {
    public void save(Date date, int days, String departureCity,
                     Date departureTime, BigDecimal price, Hotel hotel, Food food);
    public void update(long id, Date date, int days, String departureCity,
                       Date departureTime, BigDecimal price, Hotel hotel, Food food);
    public void delete(long id);
    public Tour findById(long id);
    public List<Tour> getAll();
}
