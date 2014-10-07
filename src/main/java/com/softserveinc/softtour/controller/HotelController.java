package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.service.CountryService;
import com.softserveinc.softtour.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public @ResponseBody List<Hotel> findHotels(
            @RequestParam(value = "country", required = true) List<String> country,
            @RequestParam(value = "rating", required = false) Integer rating,
            @RequestParam(value = "comfort", required = false) Integer comfort,
            @RequestParam(value = "cleanliness", required = false) Integer cleanliness,
            @RequestParam(value = "location", required = false) Integer location,
            @RequestParam(value = "valueForMoney", required = false) Integer valueForMoney,
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        return hotelService.findByCustomParameters(country, rating, comfort, cleanliness, location, valueForMoney,
                new PageRequest(--pageNum, pageSize));
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody List<Hotel> findByName(
            @RequestParam(value = "name", required = false) String name){
        return hotelService.findByName(name);
    }


    @RequestMapping(value = "/allCountry")
    public @ResponseBody List<Country> findCounties(){
        return countryService.findAll();
    }

    @RequestMapping(value = "/all")
    public @ResponseBody List<Hotel> findAllHotels(
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return hotelService.findAll(new PageRequest(--pageNum, pageSize));
    }
}
