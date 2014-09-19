package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping("/all")
    public @ResponseBody
    List<Hotel> findAllHotels() {
        return hotelService.findAll();
    }

}
