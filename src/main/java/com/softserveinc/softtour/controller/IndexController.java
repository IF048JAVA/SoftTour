package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.entity.*;
import com.softserveinc.softtour.parsers.impl.ItTourParser;
import com.softserveinc.softtour.service.*;
import com.softserveinc.softtour.util.ItTourParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private TourService tourService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public @ResponseBody List<Tour> findTours(
            @RequestParam(value = "country", required = true) String country,
            @RequestParam(value = "minPrice", required = false) Integer minPrice,
            @RequestParam(value = "maxPrice", required = false) Integer maxPrice) {

        //return tourService.findAll();
        return tourService.findByCountryAndPrice(country, minPrice, maxPrice);
    }

    @RequestMapping(value="/parseTour", method = RequestMethod.POST)
    public @ResponseBody List<Tour> searchTour(
            @RequestParam(value = "country", required = true) String country,
            @RequestParam(value = "minPrice", required = false) Integer minPrice,
            @RequestParam(value = "maxPrice", required = false) Integer maxPrice){
        //return tourService.findAll();
        String url = new ItTourParserUtil().createUrl(country, 3, 1 ,minPrice, maxPrice);
        ItTourParser parser = new ItTourParser(country);
        List<Tour> resultList = parser.parse(url);
        return resultList;

    }

    @RequestMapping(value="/saveFavorites", method = RequestMethod.POST)
    public @ResponseBody void saveFavorites(@RequestBody(required = true) final Tour currentTour){
        java.util.Date utilDate = new java.util.Date (System.currentTimeMillis());
        Date sqlDate = new Date(utilDate.getTime());
        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser =userService.findByEmail(loggedUserEmail);
        Hotel currentHotel = currentTour.getHotel();
        Food currentFood = currentTour.getFood();
        Region currentRegion = currentHotel.getRegion();
        Country currentCountry = currentRegion.getCountry();
        Country country = countryService.save(currentCountry);
        Food food = foodService.save(currentFood);
        currentRegion.setCountry(country);
        Region region = regionService.save(currentRegion);
        currentHotel.setRegion(region);
        Hotel hotel = hotelService.save(currentHotel);
        currentTour.setHotel(hotel);
        currentTour.setFood(food);
        currentTour.setDepartureCity("Null");
        currentTour.setDepartureTime(new Time(12354));
        Tour tourToFav=tourService.save(currentTour);
        Favorite favorite=new Favorite(sqlDate,currentUser,tourToFav);
        favoriteService.save(favorite);
    }
}
