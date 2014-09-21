package com.softserveinc.softtour.controller;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.softtour.entity.Role;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.entity.template.Sex;
import com.softserveinc.softtour.service.UserService;

/**
 * @author Andriy
 * Gets the data about user and processes their
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	/**
	 * Creates the object of the UserServiceImpl class 
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * Saves the object user to the table User
	 * @param user - it's object which will be saved
	 * @param sexString - it's string which contains the value male or female
	 * and which we need to cast to the type Sex 
	 * @return the name of the main page 
	 */
	@RequestMapping(value="/save", method=RequestMethod.GET)
	public String saveUser(User user, @RequestParam String sexString) {
		
		user.setAge(calculateAge(user.getBirthday()));
		user.setSex(defineSex(sexString));
		
		// Need to use --  Role role = roleServise.findById(3) !!!
		// Need to think  how to define role !!!
		Role role = new Role();
		role.setId(3);
		role.setName("registeredUser");
		user.setRole(role);
		
		userService.save(user);
		
		return "index";
	}
	
	/**
	 * Defines the sex for user
	 * @param sexString - it's string which contains the value male or female
	 * and which we need to cast to the type Sex 
	 * @return the sex for user
	 */
	private Sex defineSex(String sexString) {
		if (sexString.equals("male")) {
			return Sex.MALE;
		} else if (sexString.equals("female")) {
			return Sex.FEMALE;
		} 
		return null;
	}

	/**
	 * Calculates the age of user
	 * @param birthday - it's user's birthday 
	 * @return the age of user
	 */
	private byte calculateAge(Date birthday){
		Calendar day = Calendar.getInstance();
	    Calendar today = Calendar.getInstance();
	 
	    day.setTime(birthday);
	    // include day of birth
	    day.add(Calendar.DAY_OF_MONTH, -1);
	 
	   byte age = (byte) (today.get(Calendar.YEAR) - day.get(Calendar.YEAR));
	  
	   if (today.get(Calendar.DAY_OF_YEAR) < day.get(Calendar.DAY_OF_YEAR)) {
	       return age--;
	    }
	   return age;
	}
	
}