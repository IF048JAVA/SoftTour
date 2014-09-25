package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.service.FavoriteService;
import com.softserveinc.softtour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/userProfile")
public class UserProfileConroller {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserService userService;

// TestUser

// @RequestMapping(value="/currentUser", method= RequestMethod.POST)
// public @ResponseBody List<Favorite> findUserFavorites(
// @RequestParam(value = "user", required = true) User user
// ){
// return favoriteService.findByUser(user);
// }

// @RequestMapping(value = "/userFavorite", method = RequestMethod.POST)
// public List <Favorite> findUserFavorites(@RequestParam User user, ModelMap model) {
// model.addAttribute("currentUser", user);
// List <Favorite> currentUserFavorites = favoriteService.findByUser(user);
// model.addAttribute("currentUserFavorites", currentUserFavorites);
// return currentUserFavorites;
// }

// @RequestMapping(value = "/userFavorite", method = RequestMethod.GET)
// public @ResponseBody List <Favorite> findUserFavorites(ModelMap model) {
// model.addAttribute("currentUser", userService.findById(1));
// List <Favorite> currentUserFavorites = favoriteService.findByUser(userService.findById(1));
// model.addAttribute("currentUserFavorites", currentUserFavorites);
// return currentUserFavorites;
// }

    @RequestMapping(value = "/userFavorite", method = RequestMethod.GET)
    public @ResponseBody List<Favorite> findUserFavorites() {
        List<Favorite> currentUserFavorites = favoriteService.findByUser(userService.findById(1));
        return currentUserFavorites;
    }
}