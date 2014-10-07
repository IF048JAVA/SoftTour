package com.softserveinc.softtour.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.service.UserService;
/**
 * @author Andriy
 * Processes data from login.jsp
 */
@Component
public class LoginValidator implements Validator{

	/**
	 *  Creates the object of the UserServiceImpl class 
	 */
	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		User currentUser = (User) target;
		User user = userService.findByEmail(currentUser.getEmail());
		
		if (user == null) {
			errors.rejectValue("email", "error_email", "ПОМИЛКА ! Користувача із даним email не існує");
			
		}else {
			String hashedPassword = EncodePassword.encode(currentUser.getPassword());
			String hashPasswordDB = user.getPassword();
			
			if (!hashPasswordDB.equals(hashedPassword)) {
				errors.rejectValue("password", "error_password", "ПОМИЛКА ! Невірно введений пароль");
			}
		}
	}

}