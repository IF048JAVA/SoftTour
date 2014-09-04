package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.dao.HistoryRequestDao;
import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.HistoryRequest;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.service.HistoryRequestService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class HistoryRequestServiceImpl implements HistoryRequestService {

    private HistoryRequestDao historyRequestDao;

    public void setHistoryRequestDao(HistoryRequestDao historyRequestDao) {
        this.historyRequestDao = historyRequestDao;
    }

    @Override
    public void save(String cityFrom, Date dateFrom, Date dateTo, int daysFrom, int daysTo, Set<String> stars, int adults, int children,
                     BigDecimal priceFrom, BigDecimal priceTo, User user, Country country, Date requestDate) {
        this.historyRequestDao.save(cityFrom, dateFrom, dateTo, daysFrom, daysTo, stars, adults, children, priceFrom, priceTo, user, country, requestDate);
    }

    @Override
    public void update(long id, String cityFrom, Date dateFrom, Date dateTo, int daysFrom, int daysTo, Set<String> stars, int adults, int children,
                       BigDecimal priceFrom, BigDecimal priceTo, User user, Country country, Date requestDate) {
        this.historyRequestDao.update(id, cityFrom, dateFrom, dateTo, daysFrom, daysTo, stars, adults, children, priceFrom, priceTo, user, country, requestDate);
    }

    @Override
    public void delete(long id) {
        this.historyRequestDao.delete(id);
    }

    @Override
    public HistoryRequest findById(long id) {
        return this.historyRequestDao.findById(id);
    }

    @Override
    public List<HistoryRequest> findByUser(User user) {
        return this.historyRequestDao.findByUser(user);
    }

    @Override
    public List<HistoryRequest> findByStars(int... stars) {
        return null;
    }

    @Override
    public List<HistoryRequest> findByCountry(Country country) {
        return this.historyRequestDao.findByCountry(country);
    }

    @Override
    public List<HistoryRequest> findByRequestDate(Date requestDate) {
        return this.historyRequestDao.findByRequestDate(requestDate);
    }

    @Override
    public List<HistoryRequest> getAll() {
        return this.historyRequestDao.getAll();
    }
}
