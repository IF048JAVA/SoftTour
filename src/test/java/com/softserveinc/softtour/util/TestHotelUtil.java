package com.softserveinc.softtour.util;

import com.softserveinc.softtour.entity.Hotel;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class TestHotelUtil extends Assert {

    private HotelUtil hotelUtil = new HotelUtil();

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
    public void testUpdateHotelRate() {
        Hotel actualHotel = hotelUtil.updateHotelRate(hotel, cleanliness, comfort, location, valueForMoney);
        assertEquals(actualHotel.getName(), expectedHotel.getName());
        assertEquals(actualHotel.getRating(), expectedHotel.getRating());
        assertEquals(actualHotel.getComfort(), expectedHotel.getComfort());
        assertEquals(actualHotel.getCleanliness(), expectedHotel.getCleanliness());
        assertEquals(actualHotel.getLocation(), expectedHotel.getLocation());
        assertEquals(actualHotel.getValueForMoney(), expectedHotel.getValueForMoney());
        assertEquals(actualHotel.getFeedbackNum(), expectedHotel.getFeedbackNum());
    }
}
