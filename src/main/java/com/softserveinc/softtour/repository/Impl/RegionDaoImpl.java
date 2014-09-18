package com.softserveinc.softtour.repository.Impl;


import com.softserveinc.softtour.repository.RegionDao;
import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.Region;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class RegionDaoImpl extends HibernateDaoSupport implements RegionDao{
    @Override
    public void save (String name, Country country) {
        Region region = new Region (name, country);
        getHibernateTemplate().save(region);
    }
    @Override
    public void update (long id, String name, Country country) {
        Region region = (Region) getHibernateTemplate().get(Region.class,id);
        if(region != null) {
            region.setCountry(country);
            region.setName(name);
            getHibernateTemplate().update(region);
        } else
            System.err.println("Error!!!");
    }
    @Override
    public void delete (long id) {
        Region region = (Region)getHibernateTemplate().get(Region.class,id);
        if (region != null)
            getHibernateTemplate().delete(region);
        else
            System.err.println("Nothing to delete!!!");
    }
    @Override
    public Region findById (long id) {
            Region region = (Region) getHibernateTemplate().get(Region.class, id);
            return region;
    }
    @Override
    public List<Region> findByName (String name) {
        List<Region> list = (List<Region>) getHibernateTemplate().find("FROM Region WHERE name = ?",name);
        return list;
    }
    @Override
    public List<Region> findByCountry (Country country) {
        List<Region> list = (List<Region>) getHibernateTemplate().find("FROM Region WHERE id = ?",country.getId());
        return list;
    }
    @Override
    public List<Region> getAll() {
        List<Region> list = (List<Region>) getHibernateTemplate().find("FROM Region");
        return list;
    }
}
