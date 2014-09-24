package com.softserveinc.softtour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping(value = "/")
public class RootController {

    @RequestMapping(value = "/")
    public String homePage() {
        return "index";
    }

    @RequestMapping(value = "/search")
    public String searchPage() {
        return "search";
    }

    @RequestMapping(value = "/registration")
    public String registrationPage() {
        return "registration";
    }

    @RequestMapping(value = "/about")
    public String aboutPage() {
        return "about";
    }

    @RequestMapping(value = "/userProfile")
    public String userProfile() {
        return "userProfile";
    }

    @RequestMapping(value = "/feedback")
    public String feedback() {
        return "feedback";
    }

    @RequestMapping(value = "/hotels")
    public String hotels() {
        return "hotels";
    }
}
