package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.entity.HistoryRecord;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.repository.HistoryRecordsRepository;
import com.softserveinc.softtour.service.HistoryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryRecordServiceImpl implements HistoryRecordService {

    @Autowired
    private HistoryRecordsRepository historyRecordsRepository;

    @Override
    public HistoryRecord save(HistoryRecord historyRecord){ return  historyRecordsRepository.save(historyRecord);}

    @Override
    public void delete(long id){historyRecordsRepository.delete(id);}

    @Override
    public HistoryRecord findById(long id){return historyRecordsRepository.findOne(id);}

    @Override
    public List<HistoryRecord> findByUser(User user) {
        return historyRecordsRepository.findByUser(user);
    }

    @Override
    public List<HistoryRecord> getAll(){return historyRecordsRepository.findAll();}

    @Override
    public HistoryRecord findByUserAndTour(HistoryRecord historyRecord){
        return historyRecordsRepository.findByUserAndTour(historyRecord.getUser(),historyRecord.getTour());
    }
}
