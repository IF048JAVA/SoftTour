package com.softserveinc.softtour.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.service.UserService;

/**
 * @author Andriy
 *  Validates user's name and email
 */
@Component
public class RegistrationValidator implements Validator {

	/**
	 *  Creates the object of the UserServiceImpl class 
	 */
	@Autowired
	private UserService userService;
	
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
		
		User currentUser = (User) target;
		List<User> users = userService.findByNameOrEmail(currentUser.getName(), currentUser.getEmail());
		
		for (User registeredUser : users) {
			if (currentUser.getName().equalsIgnoreCase(registeredUser.getName())) {
				errors.rejectValue("name", "error_name", "ПОМИЛКА ! Дане ім'я уже використовується");
			}	

			if (currentUser.getEmail().equalsIgnoreCase(registeredUser.getEmail().toLowerCase())) {
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