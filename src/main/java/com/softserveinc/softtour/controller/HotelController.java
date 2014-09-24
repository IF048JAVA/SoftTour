package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Hotel> findHotels(
            @RequestParam(value = "country", required = true) String country,
            @RequestParam(value = "region", required = true) String region,
            @RequestParam(value = "rating", required = false) Integer rating,
            @RequestParam(value = "comfort", required = false) Integer comfort,
            @RequestParam(value = "cleanliness", required = false) Integer cleanliness,
            @RequestParam(value = "location", required = false) Integer location,
            @RequestParam(value = "valueForMoney", required = false) Integer valueForMoney) {

        return hotelService.findByCustomParameters(country, region, rating, comfort, cleanliness, location, valueForMoney);
    }
}
