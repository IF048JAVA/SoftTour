package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
   private TourService tourService;

    @RequestMapping(value = "/")
    public String homePage() {
        return "index";
    }

}
