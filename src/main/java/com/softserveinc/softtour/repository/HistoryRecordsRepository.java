package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.HistoryRecord;
import com.softserveinc.softtour.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRecordsRepository extends JpaRepository<HistoryRecord, Long> {

    public List<HistoryRecord> findByUser(User user);

}
