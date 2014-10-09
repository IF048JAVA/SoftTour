package com.softserveinc.softtour.util;

import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.service.FeedbackService;
import com.softserveinc.softtour.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class HotelUtil {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private FeedbackService feedbackService;

    public Hotel updateHotelRate(Long hotelId, Integer cleanliness, Integer comfort,
                                 Integer location, Integer valueForMoney) {

        Hotel hotel = hotelService.findOne(hotelId);

        Integer numOfFeedbacks = hotel.getFeedbackNum();

        Integer rating = (comfort + cleanliness + location + valueForMoney) / 4;

        BigDecimal newComfort = calculateRate(hotel.getComfort(), comfort, numOfFeedbacks);
        BigDecimal newCleanliness = calculateRate(hotel.getCleanliness(), cleanliness, numOfFeedbacks);
        BigDecimal newLocation = calculateRate(hotel.getLocation(), location, numOfFeedbacks);
        BigDecimal newValueForMoney = calculateRate(hotel.getValueForMoney(), valueForMoney, numOfFeedbacks);

        BigDecimal newRating = calculateRate(hotel.getRating(), rating, numOfFeedbacks);

        hotel.setRating(newRating);
        hotel.setValueForMoney(newValueForMoney);
        hotel.setLocation(newLocation);
        hotel.setComfort(newComfort);
        hotel.setCleanliness(newCleanliness);
        hotel.setFeedbackNum(++numOfFeedbacks);

        return hotel;
    }

    private BigDecimal calculateRate(BigDecimal oldValue, Integer newValue, Integer numOfFeedbacks) {

        BigDecimal oldRate = oldValue.multiply(toBigDecimal(numOfFeedbacks));

        return (oldRate.add(toBigDecimal(newValue))).divide(toBigDecimal(++numOfFeedbacks), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal toBigDecimal(Integer value) {
        return BigDecimal.valueOf(value);
    }
}
