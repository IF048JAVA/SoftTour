package com.softserveinc.softtour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.service.RoleService;
import com.softserveinc.softtour.service.UserService;
import com.softserveinc.softtour.util.EncodePassword;
import com.softserveinc.softtour.util.RegistrationValidator;

/**
 * @author Andriy
 * Processes data from registration.jsp
 */
@Controller
@RequestMapping(value="/registration")
public class RegistrationController {
	
	/**
	 *  Creates the object of the UserServiceImpl class 
	 */
	@Autowired
	private UserService userService;
	
	/**
	 *  Creates the object of the RoleServiceImpl class 
	 */
	@Autowired
	private RoleService roleService;
	
	/**
	 *  Creates the object of the RegistrationValidator class 
	 */
	@Autowired
	private RegistrationValidator registrationValidator; 
	
	/**
	 * Creates the user's object which we use for adding data into the database
	 * @return the name which redirect to the page registration.jsp
	 */
	@RequestMapping(value="/new")
	public String registrationUser(Model model){
		model.addAttribute(new User());
		return "registration";
	}
	
	/**
	 * Saves the object user to the table User
	 * @param user - it's object which will be saved
	 * @return the name which redirect to the page registration.jsp or userProfile.jsp
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(User user, BindingResult bindingResult) {
		
		registrationValidator.validate(user, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			user.setPassword(EncodePassword.encode(user.getPassword()));
			user.setRole(roleService.findByName("registeredUser"));
			userService.save(user);
        	
        	return "userProfile";
		} else {
			return "registration";
		}
	}
	
}