package com.softserveinc.softtour.service.Impl;


import com.softserveinc.softtour.dao.HistoryRecordsDao;
import com.softserveinc.softtour.entity.HistoryRecord;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.service.HistoryRecordsService;

import java.sql.Date;
import java.util.List;

public class HistoryRecordsServiceImpl implements HistoryRecordsService{
    private HistoryRecordsDao historyRecordsDao;

    public void setHistoryRecordsDao (HistoryRecordsDao historyRecordsDao) {this.historyRecordsDao = historyRecordsDao;}
    @Override
    public void save(Date date, User user, Tour tour){historyRecordsDao.save(date, user, tour);}
    @Override
    public void update(long id, Date date, User user, Tour tour){historyRecordsDao.update(id, date, user, tour);}
    @Override
    public void delete(long id){historyRecordsDao.delete(id);}
    @Override
    public HistoryRecord findById(long id){return historyRecordsDao.findById(id);}
    @Override
    public List<HistoryRecord> findByTour(Tour tour){return historyRecordsDao.findByTour(tour);}
    @Override
    public List<HistoryRecord> findByDate(Date date){return historyRecordsDao.findByDate(date);}
    @Override
    public List<HistoryRecord> getAll(){return historyRecordsDao.getAll();}
}
