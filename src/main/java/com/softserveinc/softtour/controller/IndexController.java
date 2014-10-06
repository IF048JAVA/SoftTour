package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.parsers.impl.TyrComUaParser;
import com.softserveinc.softtour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private TourService tourService;

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public @ResponseBody List<Tour> findTours(
            @RequestParam(value = "country", required = true) String country,
            @RequestParam(value = "minPrice", required = false) Integer minPrice,
            @RequestParam(value = "maxPrice", required = false) Integer maxPrice) {
        //return tourService.findAll();
        return tourService.findByCountryAndPrice(country, minPrice, maxPrice);
    }

    @RequestMapping(value="/parseTour", method = RequestMethod.POST)
    public @ResponseBody List<Tour> searchTour(){
        //return tourService.findAll();
        return tourService.parse();
    }

}
