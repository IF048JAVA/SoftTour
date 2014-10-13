package com.softserveinc.softtour.controller;

import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.entity.HistoryRecord;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.service.CountryService;
import com.softserveinc.softtour.service.FavoriteService;
import com.softserveinc.softtour.service.HistoryRecordService;
import com.softserveinc.softtour.service.UserService;
import com.softserveinc.softtour.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping(value = "/userProfile")
@SessionAttributes ({"user"})
public class UserProfileConroller {

    @Autowired
    private UserService userService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private HistoryRecordService historyRecordService;

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    public @ResponseBody User getCurrentUser() {
        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByEmail(loggedUserEmail);
    }

    @RequestMapping(value = "/userFavorite", method = RequestMethod.GET)
    public @ResponseBody List<Favorite> findUserFavorite() {
        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Favorite> currentUserFavorites = favoriteService.findByUser(userService.findByEmail(loggedUserEmail));
        return currentUserFavorites;
    }

    @RequestMapping(value = "/userHistory", method = RequestMethod.GET)
    public @ResponseBody List<HistoryRecord> findUserHistory() {
        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        List<HistoryRecord> currentUserRecords = historyRecordService.findByUser(userService.findByEmail(loggedUserEmail));
        return currentUserRecords;
    }

//    @RequestMapping(value="userToUpdate", method = RequestMethod.POST)
//    public @ResponseBody User updateUserProfile( @RequestBody final User userToUpdate) {
//        User updatedUser;
//
//        updatedUser = userService.findByEmail(userToUpdate.getEmail());
//        updatedUser.setName(userToUpdate.getName());
//        updatedUser.setPassword(userToUpdate.getPassword());
//        updatedUser.setBirthday(userToUpdate.getBirthday());
//        updatedUser.setSex(userToUpdate.getSex());
//        updatedUser.setPhone(userToUpdate.getPhone());
//
//        userService.save(updatedUser);
//
//        return userToUpdate;
//    }

    @RequestMapping(value="/userToUpdate", method=RequestMethod.POST)
    public String userToUpdate(User updatedUser) {
        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User userToUpdate = userService.findByEmail(loggedUserEmail);

        if (!(updatedUser.getPassword()=="")) {
            userToUpdate.setPassword(PasswordEncoder.encode(updatedUser.getPassword()));
        }
        userToUpdate.setBirthday(updatedUser.getBirthday());
        userToUpdate.setSex(updatedUser.getSex());
        userToUpdate.setPhone(updatedUser.getPhone());

        userService.save(userToUpdate);
        return "userProfile";
        }

    @RequestMapping(value="favoriteToDelete", method = RequestMethod.POST)
    public @ResponseBody Favorite deleteFavorite( @RequestBody final Favorite favoriteToDelete) {

        favoriteService.delete(favoriteToDelete.getId());
        return favoriteToDelete;
    }

}