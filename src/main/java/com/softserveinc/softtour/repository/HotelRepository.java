package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            "and h.stars > 0")
    Page<Hotel> findByCustomParameters(@Param("country") List<String> country,
                                       @Param("rating") BigDecimal rating,
                                       @Param("comfort") BigDecimal comfort,
                                       @Param("cleanliness") BigDecimal cleanliness,
                                       @Param("location") BigDecimal location,
                                       @Param("valueForMoney") BigDecimal valueForMoney,
                                       Pageable pageable);

    @Query("select h from Hotel h " +
            "where h.name like concat('%',:name,'%')" +
            "and h.stars > 0 " +
            "order by h.name asc ")
    Page <Hotel> searchHotel(@Param("name")String name,
                            Pageable pageable);

    Hotel findByName (String name);
}

