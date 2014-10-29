package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.bean.BusRoute;
import com.softserveinc.softtour.bean.TrainRoute;
import com.softserveinc.softtour.entity.*;
import com.softserveinc.softtour.parsers.BusParser;
import com.softserveinc.softtour.parsers.ItTourParser;
import com.softserveinc.softtour.parsers.TrainParser;
import com.softserveinc.softtour.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping
public class IndexController {
    ItTourParser parser;
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
    Favorite favorite;
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
            @RequestParam(value = "numberOfPage", required = true) Integer numberOfPage,
            @RequestParam(value = "travelersAdult", required = true)Integer travelersAdult,
            @RequestParam(value = "travelersChildren", required = true)Integer travelersChildren){
        //return tourService.findAll();
        //TODO get country param from database (instead of hardcode 338 - code of Egypt)
        parser = new ItTourParser(country, 338, travelersAdult, travelersChildren,minPrice, maxPrice, numberOfPage);
        List<Tour> listTour = parser.parse();
        return listTour;

    }

    @RequestMapping(value="/saveFavorites", method = RequestMethod.POST)
    public void saveFavorites(@RequestBody(required = true) final Tour currentTour){
        java.util.Date utilDate = new java.util.Date (System.currentTimeMillis());
        Date sqlDate = new Date(utilDate.getTime());
        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser =userService.findByEmail(loggedUserEmail);
        Hotel currentHotel = currentTour.getHotel();
        Region currentRegion = currentHotel.getRegion();
        Country currentCountry = currentRegion.getCountry();
        Country maybeCountry = countryService.findByName(currentCountry.getName());
        if(maybeCountry!=null)
            currentRegion.setCountry(maybeCountry);
        else
            currentRegion.setCountry(countryService.save(currentCountry));
        Region maybeRegion = regionService.findByName(currentRegion.getName());
        if(maybeRegion!=null)
            currentHotel.setRegion(maybeRegion);
        else
            currentHotel.setRegion(regionService.save(currentRegion));
        Hotel maybeHotel = hotelService.findByName(currentHotel.getName());
        if(maybeHotel!=null) {
            maybeHotel.setImgUrl(currentTour.getHotel().getImgUrl());
            maybeHotel.setStars(currentTour.getHotel().getStars());
            currentTour.setHotel(maybeHotel);
            hotelService.save(maybeHotel);
        }
        else
            {hotelService.setZero(currentHotel);
             currentTour.setHotel(hotelService.save(currentHotel));}
        currentTour.setDepartureCity("Null");
        currentTour.setDepartureTime(new Time(12354));
        Tour tourToFav=tourService.save(currentTour);
        favorite=new Favorite(sqlDate,currentUser,tourToFav);

        favoriteService.save(favorite);
    }
    @RequestMapping(value="/saveHistoryRecord", method = RequestMethod.POST)
    public void saveHistoryRecord(@RequestBody(required = true) final Tour currentTour){
        parser.setHotelImgLinkAndDepartureTime(currentTour);
        java.util.Date utilDate = new java.util.Date (System.currentTimeMillis());
        Date sqlDate = new Date(utilDate.getTime());

        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        User currentUser =userService.findByEmail(loggedUserEmail);
        Hotel currentHotel = currentTour.getHotel();
        Region currentRegion = currentHotel.getRegion();
        Country currentCountry = currentRegion.getCountry();

        Country maybeCountry = countryService.findByName(currentCountry.getName());
        if(maybeCountry!=null)
            currentRegion.setCountry(maybeCountry);
        else
            currentRegion.setCountry(countryService.save(currentCountry));

        Region maybeRegion = regionService.findByName(currentRegion.getName());
        if(maybeRegion!=null)
            currentHotel.setRegion(maybeRegion);
        else
            currentHotel.setRegion(regionService.save(currentRegion));

        Hotel maybeHotel = hotelService.findByName(currentHotel.getName());
        if(maybeHotel!=null){
            maybeHotel.setImgUrl(currentTour.getHotel().getImgUrl());
            maybeHotel.setStars(currentTour.getHotel().getStars());
            currentTour.setHotel(maybeHotel);
            hotelService.save(maybeHotel);
             }
        else
        {hotelService.setZero(currentHotel);
            currentTour.setHotel(hotelService.save(currentHotel));}
        currentTour.setDepartureTime(new Time(12354));
        Tour tourToHis=tourService.save(currentTour);
        HistoryRecord historyRecord= new HistoryRecord(sqlDate,currentUser,tourToHis);
        historyRecordService.save(historyRecord);
    }


    @RequestMapping(value="/deleteFavorites", method = RequestMethod.POST)
    public void deleteFavorites(){
        favoriteService.delete(favorite.getId());
    }

    @RequestMapping(value="/parseHotel", method = RequestMethod.POST)
    public @ResponseBody Tour parseHotelImage(@RequestBody(required = true) final Tour currentTour){
        parser.setHotelImgLinkAndDepartureTime(currentTour);
        return currentTour;
    }

    @RequestMapping(value="/trainTransitDates", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody ArrayList<TrainRoute> getTrainTransits(
                        @RequestParam(value = "currentTourId", required = true) Integer currentTourId,
                        @RequestParam(value = "cityFrom", required = true) String cityFrom){

        Tour currentTour = tourService.findOne(currentTourId);

        String departureTime = currentTour.getDepartureTime().toString().substring(0,currentTour.getDepartureTime().toString().length()-3);

        TrainParser currentTrainParser = new TrainParser(cityFrom, currentTour.getDepartureCity(), currentTour.getDate().toString(), departureTime);
        ArrayList<TrainRoute> routesList =  currentTrainParser.getRoutes();

        return routesList;
    }

    @RequestMapping(value="/busTransitDates", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody List<BusRoute> getBusTransits(
            @RequestParam(value = "currentTourId", required = true) Integer currentTourId,
            @RequestParam(value = "cityFrom", required = true) String cityFrom){

        Tour currentTour = tourService.findOne(currentTourId);

        String departureTime = currentTour.getDepartureTime().toString().substring(0,currentTour.getDepartureTime().toString().length()-3);

        BusParser currentBusParser = new BusParser(cityFrom, currentTour.getDepartureCity(), currentTour.getDate().toString(), departureTime);
        List<BusRoute> routesList =  currentBusParser.parse();

        return routesList;
    }
}
