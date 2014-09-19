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
}
