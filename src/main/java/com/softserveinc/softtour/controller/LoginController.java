package com.softserveinc.softtour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.service.UserService;
import com.softserveinc.softtour.util.LoginValidator;

@Controller
@RequestMapping(value="/login")
@SessionAttributes ({"user"})
public class LoginController {
	
	/**
	 *  Creates the object of the UserServiceImpl class 
	 */
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginValidator loginValidator;
	
	@Autowired
	private UserProfileConroller userProfileConroller;

	@RequestMapping(value="/form")	
	public String loginUser (@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout){
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		
		return "login";
	}
	
	//FIXME  Is this method  need ?!
	@RequestMapping(value="/enter", method=RequestMethod.POST)	
	public String enter (User user, BindingResult bindingResult, ModelMap model){
		loginValidator.validate(user, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			model.addAttribute(user);
			
			return "redirect:/userProfile";
		}else {
			return "login";
		}
	}
}