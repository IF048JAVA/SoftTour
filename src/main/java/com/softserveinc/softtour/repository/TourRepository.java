package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {

    @Query("select t from Tour t " +
            "where t.hotel.region.country.name = :country " +
            "and t.price between :minPrice and :maxPrice")
    List<Tour> findByCountryAndPrice(@Param("country") String country,
                                      @Param("minPrice") BigDecimal minPrice,
                                      @Param("maxPrice") BigDecimal maxPrice
    );

    @Query("select t from Tour t " +
            "where t.hotel.region.country.name = :country "+
            "and t.days = :days " +
            "and t.price = :price")
    List<Tour> findByCountryAndDaysAndPrice(@Param("country") String country,
                                       @Param("days") int days,
                                       @Param("price") BigDecimal price);

    @Query("select t from Tour t " +
            "where t.hotel.region.country.name = :country " +
            "and t.date = :date " +
            "and t.days = :days " +
            "and t.departureCity = :departureCity " +
            "and t.departureTime = :departureTime " +
            "and t.price = :price " +
            "and t.hotel.name = :hotel " +
            "and t.food = :food")
    List<Tour> findByCustomParameters(@Param("country") String country,
                                       @Param("date") Date date,
                                       @Param("days") int days,
                                       @Param("departureCity") String departureCity,
                                       @Param("departureTime") Time departureTime,
                                       @Param("price") BigDecimal price,
                                       @Param("hotel") String hotel,
                                       @Param("food") String food);
    @Query("select t from Tour t " +
            "where t.hotel.name = :hotelName " +
            "and t.date = :date " +
            "and t.days = :days " +
            "and t.price = :price")
    Tour checkTour(@Param("hotelName") String hotelName,
                          @Param("date") Date date,
                          @Param("days") int days,
                          @Param("price") BigDecimal price);
}

