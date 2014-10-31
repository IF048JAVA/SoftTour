package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.Region;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.parsers.ItTourParser;
import com.softserveinc.softtour.service.CountryService;
import com.softserveinc.softtour.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private RegionService regionService;

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "/getTour", method = RequestMethod.POST)
    public @ResponseBody List<Tour> searchTour(
            @RequestParam(value = "optionCountry", required = false) Integer optionCountry,
            @RequestParam(value = "country", required = true) String country,
            @RequestParam(value = "region", required = true) String region,
            @RequestParam(value = "twoStar", required = false) Integer twoStar,
            @RequestParam(value = "threeStar", required = false) Integer threeStar,
            @RequestParam(value = "fourStar", required = false) Integer fourStar,
            @RequestParam(value = "fiveStar", required = false) Integer fiveStar,
            @RequestParam(value = "foodOne", required = false) String foodOne,
            @RequestParam(value = "foodTwo", required = false) String foodTwo,
            @RequestParam(value = "foodThree", required = false) String foodThree,
            @RequestParam(value = "foodFour", required = false) String foodFour,
            @RequestParam(value = "foodFive", required = false) String foodFive,
            @RequestParam(value = "foodSix", required = false) String foodSix,
            @RequestParam(value = "adults", required = true) Integer adults,
            @RequestParam(value = "children", required = true) Integer children,
            @RequestParam(value = "dateFrom", required = true) String dateFrom,
            @RequestParam(value = "dateTo", required = true) String dateTo,
            @RequestParam(value = "nightFrom", required = true) Integer nightFrom,
            @RequestParam(value = "nightTo", required = true) Integer nightTo,
            @RequestParam(value = "priceFrom", required = true) Integer priceFrom,
            @RequestParam(value = "priceTo", required = true) Integer priceTo
    ){
        Set<Integer> hotelStars = new HashSet<>();
        if (twoStar != null){
            hotelStars.add(twoStar);
        }
        if (threeStar != null){
            hotelStars.add(threeStar);
        }
        if (fourStar != null){
            hotelStars.add(fourStar);
        }
        if (fiveStar != null){
            hotelStars.add(fiveStar);
        }

        Set<String> foodSet = new HashSet<>();
        if (foodOne != null){
            foodSet.add(foodOne);
        }
        if (foodTwo != null){
            foodSet.add(foodTwo);
        }
        if (foodThree != null){
            foodSet.add(foodThree);
        }
        if (foodFour != null){
            foodSet.add(foodFour);
        }
        if (foodFive != null){
            foodSet.add(foodFive);
        }
        if (foodSix != null){
            foodSet.add(foodSix);
        }

        //TODO get country & region param from database (instead of hardcode 338 - code of Egypt & 5486 - code of Dahab)
        ItTourParser parser = new ItTourParser(country, 338, 5486,  hotelStars, foodSet, adults, children, dateFrom, dateTo,
                nightFrom, nightTo, priceFrom, priceTo, 2);
        List<Tour> tourList = parser.parse();
        System.out.println(optionCountry);
        return tourList;
    }

    @RequestMapping(value = "getRegion", method = RequestMethod.POST)
    public @ResponseBody List<Region> searchRegion(){
        return regionService.findAll();
    }

    @RequestMapping(value = "getCountry", method = RequestMethod.POST)
    public @ResponseBody List<Country> searchCountry(){
        List<Country> countryList = countryService.findAll();
        System.out.println(countryList);
        return countryList;
    }
}
