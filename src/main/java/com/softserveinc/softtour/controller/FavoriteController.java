package com.softserveinc.softtour.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.service.FavoriteService;

/**
 * @author Andriy
 * Processes favorite's data
 */
@Controller
@RequestMapping(value="/favorite")
public class FavoriteController {
	
	/**
	 * Creates the object of the FavoriteServiceImpl class 
	 */
	@Autowired
	private FavoriteService favoriteService;

	/**
	 * Saves the object user to the table User
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Favorite favorite) {
		// FIXME Need to set user id
		// FIXME Need to set tour id
		
		Date date = new Date(new java.util.Date().getTime());
		favorite.setDate(date);
		
		favoriteService.save(favorite);
		
		return "redirect:/";
	}
	
	/**
	 * Deletes the object favorite with the specified id
	 * @param idString - id of the favorite's object which will be deleted 
	 * @return the name of the main page 
	 */
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@RequestParam String idString) {
		
		long id = Long.parseLong(idString);
		favoriteService.delete(id);
		
		return "redirect:/";
	}

	/**
	 * Returns the object favorite with the specified id
	 * @param idString - id of the favorite's object which will be returned
	 * @param model - it's response in which we write the favorite's object with the specified id
	 * @return the name of the main page 
	 */
	@RequestMapping(value="/findById", method=RequestMethod.GET)
	public String findById(@RequestParam String idString, Model model) {
		
		long id = Long.parseLong(idString);
		Favorite favorite = favoriteService.findById(id);
		model.addAttribute("favorite", favorite);
		
		return "redirect:/";
	}
	
	/**
	 * Returns the list of the favorite's objects with the specified parameters
	 * @param favorite - contains the parameters to search
	 * @param model - it's response in which we write the favorite's object with the specified id
	 * @return the name of the main page 
	 */
	@RequestMapping(value="/findByAnyParameters", method=RequestMethod.GET)
	public String findByAnyParameters(Favorite favorite, Model model) {
	
		List<Favorite> list = favoriteService.findByAnyParameters(favorite.getId(),
				favorite.getDate(), favorite.getUser(), favorite.getTour());
		
		model.addAttribute("favorites", list);
		return "redirect:/";
	}

	/**
	 * Returns the list of all favorite's objects which are contained in the table Favorite
	 * @param model - it's response in which we write all favorite's objects which are contained in the table Favorite
	 * @return the name of the main page 
	 */
	@RequestMapping(value="/findAll")
	public String findAll(Model model) {
		List<Favorite> list = favoriteService.findAll();
		model.addAttribute("allFavorites", list);
		
		return "redirect:/";
	}	
		
}