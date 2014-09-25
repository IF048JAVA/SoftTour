package com.softserveinc.softtour.service;


import com.softserveinc.softtour.entity.HistoryRecord;

import java.util.List;

public interface HistoryRecordsService {
    public void save(HistoryRecord historyRecord);
    public void delete(long id);
    public HistoryRecord findById(long id);

    public List<HistoryRecord> getAll();
}
