package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.Feedback;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.User;
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

import java.util.List;

@Controller
@RequestMapping(value = "/hotels")
public class HotelController {
    private static final Long TEST_USER_ID = (long) 1;

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
            @RequestParam(value = "rating", required = false) Integer rating,
            @RequestParam(value = "comfort", required = false) Integer comfort,
            @RequestParam(value = "cleanliness", required = false) Integer cleanliness,
            @RequestParam(value = "location", required = false) Integer location,
            @RequestParam(value = "valueForMoney", required = false) Integer valueForMoney,
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

        return hotelService.findByName(name, new PageRequest(page, pageSize));
    }

    @RequestMapping(value = "/allCountry")
    public @ResponseBody List<Country> findCounties() {
        return countryService.findAll();
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public void saveFeedback(
            @RequestParam(value = "comfort", required = true) Integer comfort,
            @RequestParam(value = "cleanliness", required = true) Integer cleanliness,
            @RequestParam(value = "location", required = true) Integer location,
            @RequestParam(value = "valueForMoney", required = true) Integer valueForMoney,
            @RequestParam(value = "comment", required = false) String comment,
            @RequestParam(value = "hotelId", required = true) Long hotelId) {

        Hotel hotel = hotelService.findOne(hotelId);

        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByEmail(loggedUserEmail);

        Feedback feedback = new Feedback(cleanliness, comfort, location, valueForMoney, comment,
                hotel, currentUser);
        feedbackService.save(feedback);

        hotelService.save(hotelUtil.updateHotelRate(hotelId, cleanliness, comfort, location, valueForMoney));
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public @ResponseBody List<Feedback> findByHotel(
            @RequestParam(value = "hotelId", required = true) Long hotelId) {
        return feedbackService.findByHotelId(hotelId);
    }
}
