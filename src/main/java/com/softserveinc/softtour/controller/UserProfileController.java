package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.entity.HistoryRecord;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.parsers.StaticDataParser;
import com.softserveinc.softtour.service.CountryService;
import com.softserveinc.softtour.service.FavoriteService;
import com.softserveinc.softtour.service.HistoryRecordService;
import com.softserveinc.softtour.service.UserService;
import com.softserveinc.softtour.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping(value = "/userProfile")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private HistoryRecordService historyRecordService;

    @Autowired
    private CountryService countryService;

    @Autowired
    StaticDataParser staticDataParser;

    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    public @ResponseBody User getCurrentUser() {
        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByEmail(loggedUserEmail);
        return currentUser;
    }

    @RequestMapping(value = "/userFavorite", method = RequestMethod.GET)
    public @ResponseBody List<Favorite> findUserFavorite() {
        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByEmail(loggedUserEmail);
        List<Favorite> currentUserFavorites = favoriteService.findByUser(currentUser);
        return currentUserFavorites;
    }

    @RequestMapping(value = "/userHistory", method = RequestMethod.GET)
    public @ResponseBody List<HistoryRecord> findUserHistory() {
        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByEmail(loggedUserEmail);
        List<HistoryRecord> currentUserHistory = historyRecordService.findByUser(currentUser);
        return currentUserHistory;
    }


    @RequestMapping(value="/userToUpdate", method=RequestMethod.POST)
    public String userProfile(User updatedUser) {
        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByEmail(loggedUserEmail);

        if (!(updatedUser.getPassword()=="")) {
            currentUser.setPassword(PasswordEncoder.encode(updatedUser.getPassword()));
        }
        currentUser.setBirthday(updatedUser.getBirthday());
        currentUser.setSex(updatedUser.getSex());
        currentUser.setPhone(updatedUser.getPhone());

        userService.save(currentUser);
        return "userProfile";
    }

    @RequestMapping(value="favoriteToDelete", method = RequestMethod.POST)
    public @ResponseBody Favorite deleteFavorite( @RequestBody final Favorite favoriteToDelete) {

        favoriteService.delete(favoriteToDelete.getId());
        return favoriteToDelete;
    }


    @RequestMapping(value = "/admin")
    public @ResponseBody Boolean updateDatabase(){
        return staticDataParser.parse();
    }

}