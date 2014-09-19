package com.softserveinc.softtour.repository.Impl;

import com.softserveinc.softtour.repository.HistoryRecordsDao;
import com.softserveinc.softtour.entity.HistoryRecord;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import java.sql.Date;
import java.util.List;

public class HistoryRecordsDaoImpl extends HibernateDaoSupport implements HistoryRecordsDao {
    @Override
    public void save(Date date, User user, Tour tour){
        HistoryRecord historyRecord = new HistoryRecord(date, user, tour);
        getHibernateTemplate().save(historyRecord);
    }
    @Override
    public void update(long id, Date date, User user, Tour tour){
        HistoryRecord historyRecord = (HistoryRecord)getHibernateTemplate().get(HistoryRecord.class, id);
        if (historyRecord != null){
            historyRecord.setDate(date);
            historyRecord.setUser(user);
            historyRecord.setTour(tour);
            getHibernateTemplate().update(historyRecord);
        } else {
            System.err.println("Error!");
        }
    }
    @Override
    public void delete(long id){
        HistoryRecord historyRecord = (HistoryRecord)getHibernateTemplate().get(HistoryRecord.class, id);
        if (historyRecord != null){
            getHibernateTemplate().delete(historyRecord);
        } else {
            System.err.println("Nothing to delete");
        }
    }
    @Override
    public HistoryRecord findById(long id){
        HistoryRecord historyRecord = (HistoryRecord)getHibernateTemplate().get(HistoryRecord.class, id);
        return historyRecord;
    }
    @Override
    public List<HistoryRecord> findByTour(Tour tour){
        List<HistoryRecord> list = (List<HistoryRecord>) getHibernateTemplate().find("FROM HistoryRecords WHERE tour_id = ?", tour.getId());
        return list;
    }
    @Override
    public List<HistoryRecord> findByDate(Date date){
        List<HistoryRecord> list = (List<HistoryRecord>) getHibernateTemplate().find("FROM HistoryRecords WHERE date = ?", date);
        return list;
    }
    @Override
    public List<HistoryRecord> getAll(){
        List<HistoryRecord> list = (List<HistoryRecord>) getHibernateTemplate().find("FROM HistoryRecords");
        return list;
    }
}
