package com.softserveinc.softtour.repository;


import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.HistoryRequest;
import com.softserveinc.softtour.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface HistoryRequestRepository extends JpaRepository<HistoryRequest, Long> {

    public List<HistoryRequest> findByUser(User user);
    public List<HistoryRequest> findByStars(int... stars);
    public List<HistoryRequest> findByCountry(Country country);
    public List<HistoryRequest> findByRequestDate(Date requestDate);

}
