package com.softserveinc.softtour.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.softtour.entity.Role;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.entity.template.Sex;
import com.softserveinc.softtour.service.UserService;

/**
 * @author Andriy
 * Processes user's data
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
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(User user, @RequestParam String sexString) {
		
		user.setAge(calculateAge(user.getBirthday()));
		user.setSex(defineSex(sexString));
		
		// FIXME Need to use --  Role role = roleServise.findByName("registeredUser") !!!
		// Temporary code ...   
		Role role = new Role();
		role.setId(3);
		role.setName("registeredUser");
		user.setRole(role);
		
		userService.save(user);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(User user, @RequestParam String sexString, Model model) {
		
		// FIXME Need to set id !?
		long id= 0;
		
		user.setAge(calculateAge(user.getBirthday()));
		user.setSex(defineSex(sexString));
		
		// FIXME Need to use --  Role role = roleServise.findByName("registeredUser") !!!
		// Temporary code ... !
		Role role = new Role();
		role.setId(3);
		role.setName("registeredUser");
		user.setRole(role);		
		
		userService.update(id, user);
		
		return "redirect:/userProfile";
	}
	
	/**
	 * Deletes the object user with the specified id
	 * @param idString - id of the user's object which will be deleted
	 * @return the name of the main page 
	 */
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@RequestParam String idString) {
		
		long id = Long.parseLong(idString);
		userService.delete(id);
		
		return "redirect:/index";
	}
	
	/**
	 * Returns the object user with the specified id
	 * @param idString - id of the user's object which will be returned
	 * @param model - it's response in which we write the user's object with the specified id
	 * @return the name of the main page 
	 */
	@RequestMapping(value="/findById", method=RequestMethod.GET)
	public String findById(@RequestParam String idString, Model model) {
		
		long id = Long.parseLong(idString);
		User user = userService.findById(id);
		model.addAttribute("user", user);
		
		return "redirect:/index";
	}
	
	/**
	 * Returns the list of the user's objects with the specified parameters
	 * @param user - contains the parameters to search
	 * @param model - it's response in which we write the user's object with the specified id
	 * @return the name of the main page 
	 */
	@RequestMapping(value="/findByAnyParameters", method=RequestMethod.GET)
	public String findByAnyParameters(User user, Model model) {
		
		List<User> list = userService.findByAnyParameters(user.getId(), user.getName(),
				user.getEmail(), user.getPassword(), user.getBirthday(), user.getAge(),
				user.getSex(), user.getPhone(), user.getRole());
		
		model.addAttribute("users", list);
		return "redirect:/index";
	}

	/**
	 * Returns the list of all user's objects which are contained in the table User
	 * @param model - it's response in which we write all user's objects which are contained in the table User
	 * @return the name of the main page 
	 */
	@RequestMapping(value="/findAll")
	public String findAll(Model model) {
		List<User> list = userService.findAll();
		model.addAttribute("allUsers", list);
		
		return "redirect:/index";
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