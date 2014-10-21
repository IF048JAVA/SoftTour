package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.parsers.ItTourParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/getTour", method = RequestMethod.GET)
    public @ResponseBody List<Tour> searchTour(){
        Set<Integer> hotelStars = new HashSet<>();
        hotelStars.add(5);

        Set<String> foodSet = new HashSet<>();
        foodSet.add("AI");
        ItTourParser parser = new ItTourParser("Туреччина", "Аланья", hotelStars, foodSet, 2, 1, "01.11.14", "31.12.14",
                5, 15, 500, 5000, 2);
        List<Tour> tourList = parser.parse();
        return tourList;
    }
}
