package com.softserveinc.softtour.dao;

import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Region;
import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 */
public interface HotelDao {
    public void save(String name, int stars, Region region);
    public void update(long id, String name, int stars, Region region);
    public void delete(long id);
    public Hotel findById(long id);
    public List<Hotel> findByName(String...name);
    public List<Hotel> findByStars(int...stars);
    public List<Hotel> findByRegion(Region...region);
    public List<Hotel> getAll();
}
