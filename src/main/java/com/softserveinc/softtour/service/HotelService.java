package com.softserveinc.softtour.service;

import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Region;

import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 * Поки-що роблю тільки один findBy, решту закину пізніше
 */
public interface HotelService {
    public void save(String name, int stars, Region region);
    public void update(long id, String name, int stars, Region region);
    public void delete(long id);
    public Hotel findById(long id);
    public List<Hotel> getAll();
}
