package com.softserveinc.softtour.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.parsers.TrainParser;
import com.softserveinc.softtour.service.RoleService;
import com.softserveinc.softtour.service.UserService;
import com.softserveinc.softtour.util.PasswordEncoder;
import com.softserveinc.softtour.util.RegistrationValidator;

/**
 * @author Andriy
 * Processes data from registration.jsp
 */
@Controller
@RequestMapping(value="/registration")
public class RegistrationController {
	private static final String ROLE_USER = "ROLE_USER";
	private static final Logger LOG = LoggerFactory.getLogger(TrainParser.class);
	
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
	 *Creates the object of the ProviderManager class;
	 */
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/**
	 *Creates the object of the JdbcDaoImpl class;
	 */
	@Autowired 
	private UserDetailsService userDetailsService;
	
	/**
	 * Creates the user's object which we use for adding data into the database
	 * and opens registration form
	 * @return the name which redirect to the page registration.jsp
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String openRegistrationForm(Model model){
		model.addAttribute(new User());
		return "registration";
	}
	
	/**
	 * Saves the object user to the table User
	 * @param user - it's object which will be saved
	 * @return the name which redirect to the page index.jsp or registration.jsp 
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String saveUser(User user, BindingResult bindingResult) {
		
		registrationValidator.validate(user, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			
			String password = user.getPassword();
			user.setPassword(PasswordEncoder.encode(password));
			user.setRole(roleService.findByName(ROLE_USER));
			userService.save(user);
        	
			user.setPassword(password);
			performLogin(user);

			return "redirect:/";
		} else {
			return "registration";
		}
	}

	/**
	 * Performs logging a current user
	 * @param user which will be logged
	 */
	private void performLogin(User user) {
	    try {
	      UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
	      UsernamePasswordAuthenticationToken authenticationToken = 
	    		  new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
	      authenticationManager.authenticate(authenticationToken);
	 
	      if(authenticationToken.isAuthenticated()) {
	        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	      }
	    } catch (Exception e) {
	    	LOG.error("Error of auto login user.");
	    }
	}
}