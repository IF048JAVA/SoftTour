package com.softserveinc.softtour.dao.Impl;

import com.softserveinc.softtour.dao.HotelDao;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Region;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.util.List;

/**
 * Created by oleksandrgasenuk on 04.09.14.
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
    public List<Hotel> findByName(String...name) {
        // create query
        StringBuilder stringQueryBuilder = new StringBuilder("FROM Hotel WHERE");
        for(int i = 0; i < name.length; i++){
            stringQueryBuilder.append(" name = ? OR");
        }
        stringQueryBuilder.delete(stringQueryBuilder.length()-3, stringQueryBuilder.length()); //remove last "OR"
        String queryString = stringQueryBuilder.toString();

        List<Hotel> list = (List<Hotel>) getHibernateTemplate().find(queryString, name);
        return list;
    }

    @Override
    public List<Hotel> findByStars(int...stars) {
        // create query
        StringBuilder stringQueryBuilder = new StringBuilder("FROM Hotel WHERE");
        for(int i = 0; i < stars.length; i++){
            stringQueryBuilder.append(" stars = ? OR");
        }
        stringQueryBuilder.delete(stringQueryBuilder.length()-3, stringQueryBuilder.length()); //remove last OR
        String queryString = stringQueryBuilder.toString();

        List<Hotel> list = (List<Hotel>) getHibernateTemplate().find(queryString, stars);
        return list;
    }

    @Override
    public List<Hotel> findByRegion(Region...region) {
        // create query
        StringBuilder stringQueryBuilder = new StringBuilder("FROM Hotel WHERE");
        for(int i = 0; i < region.length; i++){
            stringQueryBuilder.append(" region_id = ? OR");
        }
        stringQueryBuilder.delete(stringQueryBuilder.length()-3, stringQueryBuilder.length()); //remove last OR
        String queryString = stringQueryBuilder.toString();

        //create & initialize regions id array
        long[] regionsId = new long[region.length];
        for(int i = 0; i < region.length; i++){
            regionsId[i] = region[i].getId();
        }

        List<Hotel> list = (List<Hotel>) getHibernateTemplate().find(queryString, regionsId);
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Hotel> getAll() {
        List<Hotel> list = (List<Hotel>) getHibernateTemplate().find("FROM Hotel");
        return list;
    }
}
