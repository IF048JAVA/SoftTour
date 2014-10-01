package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("select h from Hotel h " +
            "where (h.region.country.name in (:country) " +
            "or 'allCountries' in (:country)) " +
            "and h.rating >= :rating " +
            "and h.comfort >= :comfort " +
            "and h.cleanliness >= :cleanliness " +
            "and h.location >= :location " +
            "and h.valueForMoney >= :valueForMoney " +
            "order by h.rating desc")
    List<Hotel> findByCustomParameters(@Param("country") List<String> country,
                                       @Param("rating") BigDecimal rating,
                                       @Param("comfort") BigDecimal comfort,
                                       @Param("cleanliness") BigDecimal cleanliness,
                                       @Param("location") BigDecimal location,
                                       @Param("valueForMoney") BigDecimal valueForMoney);

    @Query("select h from Hotel h " +
            "where h.name like concat('%',:name,'%')")
    List <Hotel> findByName(@Param("name")String name);
}

