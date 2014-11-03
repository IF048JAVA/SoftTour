package com.softserveinc.softtour.util;

import com.softserveinc.softtour.entity.Hotel;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class TestHotelRatingCalculator extends Assert {

    private HotelRatingCalculator hotelRatingCalculator = new HotelRatingCalculator();

    private Hotel expectedHotel;
    private Hotel hotel;
    private final BigDecimal location = BigDecimal.valueOf(2);
    private final BigDecimal cleanliness = BigDecimal.valueOf(2);
    private final BigDecimal valueForMoney = BigDecimal.valueOf(1);
    private final BigDecimal comfort = BigDecimal.valueOf(1);

    @BeforeClass
    private void setHotel() {
        hotel = new Hotel();
        hotel.setFeedbackNum(20);
        hotel.setRating(BigDecimal.valueOf(4.2));
        hotel.setLocation(BigDecimal.valueOf(3.8));
        hotel.setCleanliness(BigDecimal.valueOf(4.5));
        hotel.setComfort(BigDecimal.valueOf(4.4));
        hotel.setValueForMoney(BigDecimal.valueOf(4.1));
    }

    @BeforeClass
    private void setExpectedHotel() {
        expectedHotel = new Hotel();
        expectedHotel.setFeedbackNum(21);
        expectedHotel.setRating(BigDecimal.valueOf(4.1));
        expectedHotel.setLocation(BigDecimal.valueOf(3.7));
        expectedHotel.setCleanliness(BigDecimal.valueOf(4.4));
        expectedHotel.setComfort(BigDecimal.valueOf(4.2));
        expectedHotel.setValueForMoney(BigDecimal.valueOf(4.0));
    }

    @Test
    public void testCalculateHotelRate() {
        Hotel actualHotel = hotelRatingCalculator.calculateHotelRate(hotel, cleanliness, comfort, location, valueForMoney);
        assertEquals(actualHotel, expectedHotel);
    }
}
