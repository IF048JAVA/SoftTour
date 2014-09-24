package com.softserveinc.softtour.service;


import com.softserveinc.softtour.entity.HistoryRecord;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;

import java.sql.Date;
import java.util.List;

public interface HistoryRecordsService {
    public void save(HistoryRecord historyRecord);
    public void delete(long id);
    public HistoryRecord findById(long id);

    public List<HistoryRecord> getAll();
}
