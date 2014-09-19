package com.softserveinc.softtour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/home")
public class RootController {

    @RequestMapping
    public String homePage() {
        return "test";
    }
}
