package com.softserveinc.softtour.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.softtour.entity.Role;
import com.softserveinc.softtour.entity.template.Sex;
import com.softserveinc.softtour.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/save", method=RequestMethod.GET)
	public String saveUser(@RequestParam String name, 
			@RequestParam String email,
			@RequestParam String password,
			@RequestParam Date birthday,
			@RequestParam Sex sex,
			@RequestParam String phone ) {
		
		// Need to count age
		byte age = 0;
		
		// Need to define role
		Role role = null;
		userService.save(name, email, password, birthday, age, sex, phone, role);
		
		return "/";
	}
	
}
