package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.dao.HotelDao;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Region;
import com.softserveinc.softtour.service.HotelService;
import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 */
public class HotelServiceImpl implements HotelService {
    private HotelDao hotelDao;

    @Override
    public void save(String name, int stars, Region region) {
        hotelDao.save(name, stars, region);
    }

    @Override
    public void update(long id, String name, int stars, Region region) {
        hotelDao.update(id, name, stars, region);
    }

    @Override
    public void delete(long id) {
        hotelDao.delete(id);
    }

    @Override
    public Hotel findById(long id) {
        return hotelDao.findById(id);
    }

    @Override
    public List<Hotel> findByName(String name) {
        return hotelDao.findByName(name);
    }

    @Override
    public List<Hotel> findByStars(int stars) {
        return hotelDao.findByStars(stars);
    }

    @Override
    public List<Hotel> findByRegion(Region region) {
        return hotelDao.findByRegion(region);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelDao.getAll();
    }
}
