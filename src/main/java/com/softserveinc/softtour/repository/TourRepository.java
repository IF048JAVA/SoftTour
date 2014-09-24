package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;


public interface TourRepository extends JpaRepository<Tour, Long> {
    @Query("select t from Tour t " +
            "where t.hotel.region.country.name = :country " +
            "and t.price between :minPrice and :maxPrice")
    List<Tour> findByCustomParameters(@Param("country") String country,
                                      @Param("minPrice") BigDecimal minPrice,
                                      @Param("maxPrice") BigDecimal maxPrice
                                      );

}
