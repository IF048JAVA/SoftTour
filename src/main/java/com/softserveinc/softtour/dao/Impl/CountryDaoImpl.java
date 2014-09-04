package com.softserveinc.softtour.dao.Impl;


import com.softserveinc.softtour.dao.CountryDao;
import com.softserveinc.softtour.entity.Country;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class CountryDaoImpl extends HibernateDaoSupport implements CountryDao {

    @Override
    public void save(String name) {
        Country country = new Country(name);
        getHibernateTemplate().save(country);
    }

    @Override
    public void update(long id, String name) {
        Country country = (Country) getHibernateTemplate().get(Country.class, id);

        if (country != null) {
            country.setName(name);

            getHibernateTemplate().update(country);
        } else {
            System.err.println("Error ! \n Country is null !");
        }
    }

    @Override
    public void delete(long id) {
        Country country = (Country) getHibernateTemplate().get(Country.class, id);

        if (country != null) {
            getHibernateTemplate().delete(country);
        } else {
            System.err.println("Error ! \n Country is null !");
        }

    }

    @Override
    public Country findById(long id) {
        Country country = (Country) getHibernateTemplate().get(Country.class, id);
        return country;
    }

    @Override
    public List<Country> findByName(String name) {
        List<Country> list = (List<Country>) getHibernateTemplate().find("FROM Country WHERE name = ?", name);
        return list;
    }

    @Override
    public List<Country> getAll() {
        List<Country> list = getHibernateTemplate().find("From Country");
        return list;
    }
}
