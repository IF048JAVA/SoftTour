package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.dao.HotelDao;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Region;
import com.softserveinc.softtour.service.HotelService;

import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 * Поки-що роблю тільки один findBy, решту закину пізніше
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
    public List<Hotel> getAll() {
        return hotelDao.getAll();
    }
}
