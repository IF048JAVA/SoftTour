package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.Feedback;
import com.softserveinc.softtour.parsers.ItTourParser;
import com.softserveinc.softtour.parsers.StaticDataParser;
import com.softserveinc.softtour.service.CountryService;
import com.softserveinc.softtour.service.FeedbackService;
import com.softserveinc.softtour.service.HotelService;
import com.softserveinc.softtour.service.UserService;
import com.softserveinc.softtour.util.HotelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping(value = "/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @Autowired
    private HotelUtil hotelUtil;

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public @ResponseBody Page<Hotel> findHotels(
            @RequestParam(value = "country", required = true) List<String> country,
            @RequestParam(value = "rating", required = false) BigDecimal rating,
            @RequestParam(value = "comfort", required = false) BigDecimal comfort,
            @RequestParam(value = "cleanliness", required = false) BigDecimal cleanliness,
            @RequestParam(value = "location", required = false) BigDecimal location,
            @RequestParam(value = "valueForMoney", required = false) BigDecimal valueForMoney,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "property", required = false) String property) {

        return hotelService.findByCustomParameters(country, rating, comfort, cleanliness, location, valueForMoney,
                new PageRequest(page, pageSize, Sort.Direction.DESC, property));
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody Page<Hotel> findByName(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        return hotelService.searchHotel(name, new PageRequest(page, pageSize));
    }

    @RequestMapping(value = "/allCountry")
    public @ResponseBody List<Country> findCounties() {
        return countryService.findAll();
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public void saveFeedback(
            @RequestParam(value = "comfort", required = true) BigDecimal comfort,
            @RequestParam(value = "cleanliness", required = true) BigDecimal cleanliness,
            @RequestParam(value = "location", required = true) BigDecimal location,
            @RequestParam(value = "valueForMoney", required = true) BigDecimal valueForMoney,
            @RequestParam(value = "comment", required = false) String comment,
            @RequestParam(value = "hotelId", required = true) Long hotelId) {

        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByEmail(loggedUserEmail);
        Hotel hotel = hotelService.findOne(hotelId);

        Feedback feedback = new Feedback(cleanliness, comfort, location, valueForMoney, comment,
                hotel, currentUser);
        feedbackService.save(feedback);

        hotelService.save(hotelUtil.updateHotelRate(hotel, cleanliness, comfort, location, valueForMoney));
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public @ResponseBody List<Feedback> findByHotel(
            @RequestParam(value = "hotelId", required = true) Long hotelId) {
        return feedbackService.findByHotelId(hotelId);
    }

    @RequestMapping(value = "/tours", method = RequestMethod.GET)
    public  @ResponseBody List<Tour> findTours(
            @RequestParam(value = "hotelId") Long hotelId,
            @RequestParam(value = "page") Integer page){
        Hotel hotel = hotelService.findOne(hotelId);
        ItTourParser parser = new ItTourParser(hotel, page);
        List<Tour> listTour = parser.parse();

        for(Tour tour : listTour){
            tour.setHotel(hotel);
        }

        return listTour;
    }
}
