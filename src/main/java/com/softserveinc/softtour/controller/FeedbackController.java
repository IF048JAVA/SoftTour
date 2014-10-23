package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/send")
public class FeedbackController {

    @Autowired
    private MailService mailService;

    @RequestMapping(method = RequestMethod.POST)
    public String sendEmail(
            @RequestParam(value = "email", required = true) String from,
            @RequestParam(value = "area", required = true) String message,
            @RequestParam(value = "select", required = true) String subject,
            @RequestParam(value = "name", required = true) String name
    ){
        String to = "softtourtravel@gmail.com";

        mailService.sendMail(to, from, subject, message, name);

        return "feedback";
    }
}
