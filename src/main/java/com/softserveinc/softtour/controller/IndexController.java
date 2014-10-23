package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.bean.TrainRoute;
import com.softserveinc.softtour.entity.*;
import com.softserveinc.softtour.entity.template.Food;
import com.softserveinc.softtour.parsers.ItTourParser;
import com.softserveinc.softtour.parsers.TrainParser;
import com.softserveinc.softtour.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping
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
        if(maybeHotel!=null)
            currentTour.setHotel(maybeHotel);
        else
            {hotelService.setZero(currentHotel);
             currentTour.setHotel(hotelService.save(currentHotel));}
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
        if(maybeHotel!=null)
            currentTour.setHotel(maybeHotel);
        else
        {hotelService.setZero(currentHotel);
            currentTour.setHotel(hotelService.save(currentHotel));}
        currentTour.setDepartureCity("Null");
        currentTour.setDepartureTime(new Time(12354));
        Tour tourToHis=tourService.save(currentTour);
        HistoryRecord historyRecord= new HistoryRecord(sqlDate,currentUser,tourToHis);
        historyRecordService.save(historyRecord);
    }





//    @RequestMapping(value="/transitDates", method = RequestMethod.POST)
//    public @ResponseBody void getTrainTransits(@RequestBody final Tour tour){
//        System.out.println(tour);
//        System.out.println(tour);
//        System.out.println(tour);
//        System.out.println(tour);
//    }



    @RequestMapping(value="/transitDates", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody ArrayList<TrainRoute> getTrainTransits(
                        @RequestParam(value = "currentTourId", required = true) Integer currentTourId,
                        @RequestParam(value = "cityFrom", required = true) String cityFrom){

        System.out.println(currentTourId);
        System.out.println(currentTourId);
        System.out.println(currentTourId);
        System.out.println(currentTourId);

        Tour currentTour = tourService.findOne(currentTourId);

        TrainParser currentTrainParser = new TrainParser("Київ", "Львів", "2014-11-08", "23:00");
//        TrainParser currentTrainParser = new TrainParser(cityFrom, currentTour.getDepartureCity(), currentTour.getDate().toString(), currentTour.getDepartureTime());

//        System.out.println(cityFrom + currentTour.getDepartureCity() + currentTour.getDate().toString() + currentTour.getDepartureTime().toString());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@in index");
        System.out.println(currentTour);

        ArrayList<TrainRoute> routesList =  currentTrainParser.getRoutes();

//        //TEST list
//        ArrayList<TrainRoute> testList =  new ArrayList<TrainRoute>();
//        testList.add(new TrainRoute("111О","Харків","Львів","2014-11-10","04:08","09:14","13:22","199.40","673.20"));
//        System.out.println(testList);

        System.out.println(routesList);

        for (TrainRoute route : routesList) {
			System.out.println(route);
		}
        return routesList;
    }

}
