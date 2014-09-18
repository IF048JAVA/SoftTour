package com.softserveinc.softtour.dao;


import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.HistoryRequest;
import com.softserveinc.softtour.entity.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface HistoryRequestDao {

    public void save(String cityFrom, Date dateFrom, Date dateTo, int daysFrom, int daysTo, Set<String> stars, int adults, int children,
              BigDecimal priceFrom, BigDecimal priceTo, User user, Country country,Date requestDate);
    public void update(long id, String cityFrom, Date dateFrom, Date dateTo, int daysFrom, int daysTo, Set<String> stars, int adults, int children,
                BigDecimal priceFrom, BigDecimal priceTo, User user, Country country,Date requestDate);
    public void delete(long id);

    public HistoryRequest findById(long id);
    public List<HistoryRequest> findByUser(User user);
    public List<HistoryRequest> findByStars(int... stars);
    public List<HistoryRequest> findByCountry(Country country);
    public List<HistoryRequest> findByRequestDate(Date requestDate);

    public List<HistoryRequest> getAll();

}
