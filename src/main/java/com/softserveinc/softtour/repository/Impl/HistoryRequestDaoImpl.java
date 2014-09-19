package com.softserveinc.softtour.repository.Impl;


import com.softserveinc.softtour.repository.HistoryRequestDao;
import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.HistoryRequest;
import com.softserveinc.softtour.entity.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class HistoryRequestDaoImpl extends HibernateDaoSupport implements HistoryRequestDao {

    @Override
    public void save(String cityFrom, Date dateFrom, Date dateTo, int daysFrom, int daysTo, Set<String> stars, int adults, int children,
                     BigDecimal priceFrom, BigDecimal priceTo, User user, Country country, Date requestDate) {
        HistoryRequest historyRequest = new HistoryRequest(cityFrom, dateFrom, dateTo, daysFrom, daysTo, stars, adults, children,
                                                           priceFrom, priceTo, user, country, requestDate);

    }

    @Override
    public void update(long id, String cityFrom, Date dateFrom, Date dateTo, int daysFrom, int daysTo, Set<String> stars, int adults, int children,
                       BigDecimal priceFrom, BigDecimal priceTo, User user, Country country, Date requestDate) {
        HistoryRequest historyRequest = (HistoryRequest) getHibernateTemplate().get(HistoryRequest.class, id);

        if (historyRequest != null) {
            historyRequest.setCityFrom(cityFrom);
            historyRequest.setDateFrom(dateFrom);
            historyRequest.setDateTo(dateTo);
            historyRequest.setDaysFrom(daysFrom);
            historyRequest.setDateTo(dateTo);
            historyRequest.setStars(stars);
            historyRequest.setAdults(adults);
            historyRequest.setChildren(children);
            historyRequest.setPriceFrom(priceFrom);
            historyRequest.setPriceTo(priceTo);
            historyRequest.setUser(user);
            historyRequest.setCountry(country);
            historyRequest.setRequestDate(requestDate);

            getHibernateTemplate().update(historyRequest);
        } else {
            System.err.println("Error ! \n HistoryRequest is null !");
        }
    }

    @Override
    public void delete(long id) {
        HistoryRequest historyRequest = (HistoryRequest) getHibernateTemplate().get(HistoryRequest.class, id);
        if (historyRequest != null) {
            getHibernateTemplate().delete(historyRequest);
        } else {
            System.err.println("Error ! \n HistoryRequest is null !");
        }
    }

    @Override
    public HistoryRequest findById(long id) {
        HistoryRequest historyRequest = (HistoryRequest) getHibernateTemplate().get(HistoryRequest.class, id);
        return historyRequest;
    }

    @Override
    public List<HistoryRequest> findByUser(User user) {
        List<HistoryRequest> list = (List<HistoryRequest>) getHibernateTemplate().find("FROM HistoryRequest WHERE user_id = ?", user.getId());
        return list;
    }

    // Undone
    @Override
    public List<HistoryRequest> findByStars(int... stars) {
        return null;
    }

    @Override
    public List<HistoryRequest> findByCountry(Country country) {
        List<HistoryRequest> list = (List<HistoryRequest>) getHibernateTemplate().find("FROM HistoryRequest WHERE country_id = ?", country.getId());
        return list;
    }

    @Override
    public List<HistoryRequest> findByRequestDate(Date requestDate) {
        List<HistoryRequest> list = (List<HistoryRequest>) getHibernateTemplate().find("FROM HistoryRequest WHERE requestDate = ?", requestDate);
        return list;
    }

    @Override
    public List<HistoryRequest> getAll() {
        List<HistoryRequest> list = (List<HistoryRequest>) getHibernateTemplate().find("FROM HistoryRequest");
        return list;
    }
}
