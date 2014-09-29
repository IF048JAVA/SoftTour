package com.softserveinc.softtour.service;

import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.HistoryRequest;
import com.softserveinc.softtour.entity.User;

import java.util.Date;
import java.util.List;

public interface HistoryRequestService {

    public void save(HistoryRequest historyRequest);
    public void delete(long id);
    public HistoryRequest findById(long id);
    public List<HistoryRequest> findByUser(User user);
    public List<HistoryRequest> findByStars(int... stars);
    public List<HistoryRequest> findByCountry(Country country);
    public List<HistoryRequest> findByRequestDate(Date requestDate);
    public List<HistoryRequest> getAll();

}
