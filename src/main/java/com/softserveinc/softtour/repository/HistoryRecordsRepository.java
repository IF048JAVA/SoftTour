package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.HistoryRecord;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRecordsRepository extends JpaRepository<HistoryRecord, Long> {

}
