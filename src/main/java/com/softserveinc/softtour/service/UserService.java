package com.softserveinc.softtour.service;

import java.sql.Date;
import java.util.List;

import com.softserveinc.softtour.entity.Role;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.entity.template.Sex;

/**
 * @author Andriy
 * 	Contains the methods for work with table User in the SoftTour database
 */
public interface UserService {

	/**
	 * Saves the object user to the table User
	 */
	public void save(User user);
	
	/**
	 * Updates the object user with the specified id
	 * @param id - id of the object user which will be updated
	 * @param user - it's the object with the new values
	 */
	public void update(long id, User user);
	
	/**
	 *  Deletes the object user with the specified id
	 *  id - id of the object user which will be deleted
	 */
	public void delete(long id);
	
	/**
	 *  Returns the object user with the specified id
	 *  id - id of the object user which will be returned
	 */
	public User findById(long id);

    /**
     * Returns the list of the user's objects with the specified email
     */
    public User findByEmail(String name);
	
	/**
	 * Returns the list of the user's objects with the specified name or email
	 */
	public List<User> findByNameOrEmail(String name, String  email);
	
	/**
	 *  Returns the list of the user's objects with the specified parameters
	 */
	public List<User> findByAnyParameters(long id, String name, String email, String password, 
			Date birthday, Sex sex, String phone, Role role);
	
	/**
	 *  Returns the list of all user's objects which are contained in the table User
	 */
	public List<User> findAll();
	
}