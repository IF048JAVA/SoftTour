package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.entity.*;
import com.softserveinc.softtour.entity.template.Food;
import com.softserveinc.softtour.parsers.impl.ItTourParser;
import com.softserveinc.softtour.service.*;
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
    private HistoryRecordService historyRecordService;

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
            @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
            @RequestParam(value = "numberOfPage", required = true) Integer numberOfPage){
        //return tourService.findAll();
        ItTourParser parser = new ItTourParser(country, 3, 1 ,minPrice, maxPrice, numberOfPage);
        List<Tour> listTour = parser.parse();
        return listTour;

    }

    @RequestMapping(value="/saveFavorites", method = RequestMethod.POST)
    public @ResponseBody void saveFavorites(@RequestBody(required = true) final Tour currentTour){
        java.util.Date utilDate = new java.util.Date (System.currentTimeMillis());
        Date sqlDate = new Date(utilDate.getTime());
        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser =userService.findByEmail(loggedUserEmail);
        Hotel currentHotel = currentTour.getHotel();
        Region currentRegion = currentHotel.getRegion();
        Country currentCountry = currentRegion.getCountry();
        Country country = countryService.save(currentCountry);
        currentRegion.setCountry(country);
        Region region = regionService.save(currentRegion);
        currentHotel.setRegion(region);
        Hotel hotel = hotelService.save(currentHotel);
        currentTour.setHotel(hotel);
        currentTour.setDepartureCity("Null");
        currentTour.setDepartureTime(new Time(12354));
        Tour tourToFav=tourService.save(currentTour);
        Favorite favorite=new Favorite(sqlDate,currentUser,tourToFav);
        favoriteService.save(favorite);
    }
    @RequestMapping(value="/saveHistoryRecord", method = RequestMethod.POST)
    public @ResponseBody void saveHistoryRecord(@RequestBody(required = true) final Tour currentTour){
        java.util.Date utilDate = new java.util.Date (System.currentTimeMillis());
        Date sqlDate = new Date(utilDate.getTime());
        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser =userService.findByEmail(loggedUserEmail);
        Hotel currentHotel = currentTour.getHotel();
        Region currentRegion = currentHotel.getRegion();
        Country currentCountry = currentRegion.getCountry();
        Country country = countryService.save(currentCountry);
        currentRegion.setCountry(country);
        Region region = regionService.save(currentRegion);
        currentHotel.setRegion(region);
        Hotel hotel = hotelService.save(currentHotel);
        currentTour.setHotel(hotel);
        currentTour.setDepartureCity("Null");
        currentTour.setDepartureTime(new Time(12354));
        Tour tourToHis=tourService.save(currentTour);
        HistoryRecord historyRecord= new HistoryRecord(sqlDate,currentUser,tourToHis);
        historyRecordService.save(historyRecord);
    }
}
