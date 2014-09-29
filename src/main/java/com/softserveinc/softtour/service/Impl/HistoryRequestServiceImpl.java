package com.softserveinc.softtour.service.Impl;

import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.HistoryRequest;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.repository.HistoryRequestRepository;
import com.softserveinc.softtour.service.HistoryRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation= Propagation.SUPPORTS, readOnly = true)
public class HistoryRequestServiceImpl implements HistoryRequestService {

    @Autowired
    private HistoryRequestRepository historyRequestRepository;

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void save(HistoryRequest historyRequest) {
        this.historyRequestRepository.save(historyRequest);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void delete(long id) {
        this.historyRequestRepository.delete(id);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public HistoryRequest findById(long id) {
        return this.historyRequestRepository.findOne(id);
    }

    @Override
    public List<HistoryRequest> findByUser(User user) {
        return this.historyRequestRepository.findByUser(user);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public List<HistoryRequest> findByStars(int... stars) {
        return this.historyRequestRepository.findByStars(stars);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public List<HistoryRequest> findByCountry(Country country) {
        return this.historyRequestRepository.findByCountry(country);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public List<HistoryRequest> findByRequestDate(Date requestDate) {
        return this.historyRequestRepository.findByRequestDate(requestDate);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public List<HistoryRequest> getAll() {
        return this.historyRequestRepository.findAll();
    }
}
