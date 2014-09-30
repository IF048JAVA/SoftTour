package com.softserveinc.softtour.controller;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.service.UserService;

/**
 * @author Andriy
 *  Validates user's name and email
 */
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	/**
	 * Validates whether the given name or email are in table User
	 * target - it's array of objects which contains user's and userServiceImpl objects   
	 */
	@Override
	public void validate(Object target, Errors errors) {
		Object[] objs = (Object[])target;
		
		UserService userService = (UserService) objs[0];
		User currentUser = (User) objs[1];
		
		List<User> users = userService.findByNameOrEmail(currentUser.getName(), currentUser.getEmail());
		
		for (User registeredUser : users) {
			if (currentUser.getName().equalsIgnoreCase(registeredUser.getName())) {
				errors.rejectValue("name", "error_name", "ПОМИЛКА ! Дане ім'я уже використовується");
				
			}else if (currentUser.getEmail().equalsIgnoreCase(registeredUser.getEmail().toLowerCase())) {
				errors.rejectValue("email", "error_email", "ПОМИЛКА ! Даний email уже використовується");
			}
		}
		
		long birthdayInMilliseconds = currentUser.getBirthday().getTime();
		long currentTimeInMillisends = System.currentTimeMillis();
		
		if(birthdayInMilliseconds > currentTimeInMillisends) {
			errors.rejectValue("birthday", "error_date", "ПОМИЛКА ! Невірно введена дата");
		}
	}
	
}