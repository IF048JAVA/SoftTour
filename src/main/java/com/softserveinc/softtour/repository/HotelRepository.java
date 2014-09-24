package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("select h from Hotel h " +
            "where h.region.name = :region and h.region.country.name = :country " +
            "and h.rating >= :rating and h.comfort >= :comfort " +
            "and h.cleanliness >= :cleanliness and h.location >= :location " +
            "and h.valueForMoney >= :valueForMoney")
    List<Hotel> findByCustomParameters(@Param("country") String country,
                                       @Param("region") String region,
                                       @Param("rating") BigDecimal rating,
                                       @Param("comfort") BigDecimal comfort,
                                       @Param("cleanliness") BigDecimal cleanliness,
                                       @Param("location") BigDecimal location,
                                       @Param("valueForMoney") BigDecimal valueForMoney);

    List <Hotel> findByName(String name);
}

