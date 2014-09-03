package com.softserveinc.softtour.dao;

import com.softserveinc.softtour.entity.HistoryRecord;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;

import java.sql.Date;
import java.awt.List;

public interface HistoryRecordsDAO {
    public void saveHistoryRequest(Date date, User user, Tour tour);
    public void updateHistoryRequest(long id, Date date, User user, Tour id);
    public void deleteHistoryRequest(long id);
    public HistoryRecords findById(long id);
    public List<HistoryRecords> findByTour(Tour tour);
    public List<HistoryRecords> findByDate(Date date);
    public List<HistoryRecords> getAll();
}
