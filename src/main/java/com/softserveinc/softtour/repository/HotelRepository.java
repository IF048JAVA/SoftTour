package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
