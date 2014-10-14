package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Feedback;
import com.softserveinc.softtour.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("select f from Feedback f " +
            "where f.hotel.id = :hotelId")
    List<Feedback> findByHotelId(@Param("hotelId") Long hotelId);
}
