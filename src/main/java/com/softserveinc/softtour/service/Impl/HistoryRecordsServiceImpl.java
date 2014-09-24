package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.entity.HistoryRecord;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.repository.HistoryRecordsRepository;
import com.softserveinc.softtour.service.HistoryRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class HistoryRecordsServiceImpl implements HistoryRecordsService{

    @Autowired
    private HistoryRecordsRepository historyRecordsRepository;

    @Override
    public void save(HistoryRecord historyRecord){historyRecordsRepository.save(historyRecord);}

    @Override
    public void delete(long id){historyRecordsRepository.delete(id);}

    @Override
    public HistoryRecord findById(long id){return historyRecordsRepository.findOne(id);}

    @Override
    public List<HistoryRecord> getAll(){return historyRecordsRepository.findAll();}
}
