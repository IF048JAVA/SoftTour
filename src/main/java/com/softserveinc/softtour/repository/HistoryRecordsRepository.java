package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.HistoryRecord;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HistoryRecordsRepository extends JpaRepository<HistoryRecord, Long> {

    @Modifying
    @Query("UPDATE HistoryRecords SET date=?2,user_id=?3, tour_id=?4 WHERE id=?1")
    public void update(long id, Date date, User user, Tour tour);

    public List<HistoryRecord> findByIdOrDateOrUserOrTour(long id, Date date, User user, Tour tour);
}
