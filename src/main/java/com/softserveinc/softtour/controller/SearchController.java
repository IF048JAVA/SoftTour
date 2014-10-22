package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.parsers.ItTourParser;
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

    @RequestMapping(value = "/result")
    public String getSearch(){
        return "searchResult";
    }

    @RequestMapping(value = "/getTour", method = RequestMethod.POST)
    public @ResponseBody List<Tour> searchTour(
            @RequestParam(value = "country", required = true) String country,
            @RequestParam(value = "region", required = true) String region,
            @RequestParam(value = "oneStar", required = false) Integer oneStar,
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
        hotelStars.add(oneStar);
        hotelStars.add(twoStar);
        hotelStars.add(threeStar);
        hotelStars.add(fourStar);
        hotelStars.add(fiveStar);
//
        Set<String> foodSet = new HashSet<>();
        foodSet.add(foodOne);
        foodSet.add(foodTwo);
        foodSet.add(foodThree);
        foodSet.add(foodFour);
        foodSet.add(foodFive);
        foodSet.add(foodSix);
//
//        System.out.println(country);
//        System.out.println(region);
//        System.out.println(hotelStars);
//        System.out.println(foodSet);
//        System.out.println(adults);
//        System.out.println(children);
//        System.out.println(dateFrom);
//        System.out.println(dateTo);
//        System.out.println(nightFrom);
//        System.out.println(nightTo);
//        System.out.println(priceFrom);
//        System.out.println(priceTo);


//        Set<Integer> hotelStars = new HashSet<>();
//        hotelStars.add(5);

//        Set<String> foodSet = new HashSet<>();
//        foodSet.add("AI");
        ItTourParser parser = new ItTourParser(/*"Туреччина"*/ country, /*"Аланья"*/ region, hotelStars, foodSet, adults, children, dateFrom/*"01.11.14"*/, dateTo/*"31.12.14"*/,
                nightFrom, nightTo, priceFrom, priceTo, 2);
        List<Tour> tourList = parser.parse();
        System.out.println(dateFrom);
        System.out.println(dateTo);
        System.out.println(tourList);
        return tourList;
    }
}
