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

    @RequestMapping(value="/parseTour", method = RequestMethod.POST)
    public @ResponseBody List<Tour> searchTour(
            @RequestParam(value = "country", required = true) String country,
            @RequestParam(value = "countryParameter",required = true) long countryParameter,
            @RequestParam(value = "minPrice", required = false) Integer minPrice,
            @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
            @RequestParam(value = "numberOfPage", required = true) Integer numberOfPage,
            @RequestParam(value = "travelersAdult", required = true)Integer travelersAdult,
            @RequestParam(value = "travelersChildren", required = true)Integer travelersChildren){
        //return tourService.findAll();
        //TODO get country param from database (instead of hardcode 338 - code of Egypt)
        parser = new ItTourParser(country, countryParameter, travelersAdult, travelersChildren,minPrice, maxPrice, numberOfPage);
        List<Tour> listTour = parser.parse();
        return listTour;

    }

    @RequestMapping(value="/saveFavorites", method = RequestMethod.POST)
    public void saveFavorites(@RequestBody(required = true) Tour currentTour){

        java.util.Date utilDate = new java.util.Date (System.currentTimeMillis());
        Date sqlDate = new Date(utilDate.getTime());
        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser =userService.findByEmail(loggedUserEmail);
        Hotel currentHotel = currentTour.getHotel();
        Region currentRegion = currentHotel.getRegion();
        Country currentCountry = currentRegion.getCountry();

        Country maybeCountry = countryService.findByName(currentCountry.getName());
            currentRegion.setCountry(maybeCountry);

        Region maybeRegion = regionService.findByName(currentRegion.getName());
            currentHotel.setRegion(maybeRegion);

        Hotel maybeHotel = hotelService.findByName(currentHotel.getName());
            maybeHotel.setImgUrl(currentTour.getHotel().getImgUrl());
            maybeHotel.setStars(currentTour.getHotel().getStars());
            currentTour.setHotel(maybeHotel);
            hotelService.save(maybeHotel);

        currentTour.setDepartureTime(new Time(12354));

        Tour maybeTour = tourService.checkTour(currentTour);
        Tour tourToFav;

        if(maybeTour!=null)
            tourToFav = maybeTour; else {
            tourService.save(currentTour);
            tourToFav = tourService.checkTour(currentTour);}

        favorite=new Favorite(sqlDate,currentUser,tourToFav);
        Favorite maybeFavorite = favoriteService.findByUserAndTour(favorite.getUser(),favorite.getTour());
        if (maybeFavorite==null){
            favoriteService.save(favorite);
            System.out.println("Saved");
        }
    }
    @RequestMapping(value="/saveHistoryRecord", method = RequestMethod.POST)
    public void saveHistoryRecord(@RequestBody(required = true) Tour currentTour){
        parser.parseAdvanceData(currentTour);
        java.util.Date utilDate = new java.util.Date (System.currentTimeMillis());
        Date sqlDate = new Date(utilDate.getTime());
        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser =userService.findByEmail(loggedUserEmail);
        Hotel currentHotel = currentTour.getHotel();
        Region currentRegion = currentHotel.getRegion();
        Country currentCountry = currentRegion.getCountry();

        Country maybeCountry = countryService.findByName(currentCountry.getName());
            currentRegion.setCountry(maybeCountry);

        Region maybeRegion = regionService.findByName(currentRegion.getName());
            currentHotel.setRegion(maybeRegion);


        Hotel maybeHotel = hotelService.findByName(currentHotel.getName());
            currentTour.setHotel(maybeHotel);
            hotelService.save(maybeHotel);



        currentTour.setDepartureTime(new Time(12354));

        Tour maybeTour = tourService.checkTour(currentTour);
        Tour tourToHis;
        if(maybeTour!=null)
            tourToHis = maybeTour; else
        {tourService.save(currentTour);
        tourToHis = tourService.checkTour(currentTour);}
        HistoryRecord historyRecord= new HistoryRecord(sqlDate,currentUser,tourToHis);
        HistoryRecord maybeHistoryRecord = historyRecordService.findByUserAndTour(historyRecord);

        if(maybeHistoryRecord==null)
            {historyRecordService.save(historyRecord);
                System.out.println("Saved");}
    }


    @RequestMapping(value="/deleteFavorites", method = RequestMethod.POST)
    public void deleteFavorites(){
        favoriteService.delete(favorite.getId());
    }

    @RequestMapping(value="/parseHotel", method = RequestMethod.POST)
    public @ResponseBody Tour parseHotel(@RequestBody(required = true) Tour currentTour){
        System.out.println(currentTour);
        parser.parseAdvanceData(currentTour);
        System.out.println(currentTour.getHotel().getImgUrl());
        Hotel maybeHotel = hotelService.findByName(currentTour.getHotel().getName());
        maybeHotel.setImgUrl(currentTour.getHotel().getImgUrl());
        maybeHotel.setStars(currentTour.getHotel().getStars());
        hotelService.save(maybeHotel);
        return currentTour;
    }

    @RequestMapping(value="/checkFavorites", method = RequestMethod.POST)
    public @ResponseBody Boolean checkFavorites(@RequestBody(required = true)Tour currentTour){
        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser =userService.findByEmail(loggedUserEmail);
        Tour tour = tourService.checkTour(currentTour);
        java.util.Date utilDate = new java.util.Date (System.currentTimeMillis());
        Date sqlDate = new Date(utilDate.getTime());
        favorite=new Favorite(sqlDate,currentUser,tour);
        Favorite maybeFavorite = favoriteService.findByUserAndTour(favorite.getUser(),favorite.getTour());
        System.out.println("done");
        if (maybeFavorite==null)
            return false; else
            return true;

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

//    @RequestMapping(value="/busTransitDates", method = {RequestMethod.GET, RequestMethod.POST})
//    public @ResponseBody List<BusRoute> getBusTransits(
//            @RequestParam(value = "currentTourId", required = true) Integer currentTourId,
//            @RequestParam(value = "cityFrom", required = true) String cityFrom){
//
//        Tour currentTour = tourService.findOne(currentTourId);
//
//        String departureTime = currentTour.getDepartureTime().toString().substring(0,currentTour.getDepartureTime().toString().length()-3);
//
//        BusParser currentBusParser = new BusParser(cityFrom, currentTour.getDepartureCity(), currentTour.getDate().toString(), departureTime);
//        List<BusRoute> routesList =  currentBusParser.parse();
//
//        return routesList;
//    }
}
