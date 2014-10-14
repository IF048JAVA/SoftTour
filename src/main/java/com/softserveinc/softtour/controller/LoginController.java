package com.softserveinc.softtour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.softtour.service.UserService;

/**
 * @author Andrii
 * Logins a user
 */
@Controller
public class LoginController {
	
	/**
	 *  Creates the object of the UserServiceImpl class 
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * Logins a user
	 * @return the name which redirect to the page login.jsp
	 */
	@RequestMapping(value="/login")	
	public String loginUser (@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model){
		
		if (error != null) {
			model.addAttribute("error", "Invalid email or password!");
		}
		return "login";
	}
}