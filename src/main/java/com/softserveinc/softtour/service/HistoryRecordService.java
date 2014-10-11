package com.softserveinc.softtour.service;


import com.softserveinc.softtour.entity.HistoryRecord;
import com.softserveinc.softtour.entity.User;

import java.util.List;

public interface HistoryRecordService {
    public HistoryRecord save(HistoryRecord historyRecord);
    public void delete(long id);
    public HistoryRecord findById(long id);
    public List<HistoryRecord> findByUser(User user);
    public List<HistoryRecord> getAll();
}
