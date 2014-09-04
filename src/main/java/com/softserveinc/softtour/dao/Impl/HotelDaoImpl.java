package com.softserveinc.softtour.dao.Impl;

import com.softserveinc.softtour.dao.HotelDao;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Region;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
 * Поки-що роблю тільки один findBy, решту закину пізніше
 */
public class HotelDaoImpl extends HibernateDaoSupport implements HotelDao {

    @Override
    public void save(String name, int stars, Region region) {
        Hotel hotel = new Hotel(name, stars, region);
        getHibernateTemplate().save(hotel);
    }

    @Override
    public void update(long id, String name, int stars, Region region) {
        Hotel hotel = (Hotel) getHibernateTemplate().get(Hotel.class, id);
        if(hotel != null) {
            hotel.setName(name);
            hotel.setStars(stars);
            hotel.setRegion(region);
            getHibernateTemplate().update(hotel);
        } else
            System.err.println("Error!!!");
    }

    @Override
    public void delete(long id) {
        Hotel hotel = (Hotel)getHibernateTemplate().get(Hotel.class,id);
        if (hotel != null)
            getHibernateTemplate().delete(hotel);
        else
            System.err.println("Nothing to delete!!!");
    }

    @Override
    public Hotel findById(long id) {
        Hotel hotel = (Hotel) getHibernateTemplate().get(Hotel.class, id);
        return hotel;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Hotel> getAll() {
        List<Hotel> list = (List<Hotel>) getHibernateTemplate().find("FROM Hotel");
        return list;
    }
}
