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
	public void save(String name, String email, String password, Date birthday, byte age, Sex sex, String phone, Role role);

	/**
	 *  Updates the object user with the specified id
	 *  id - id of the object user which will updated
	 */
	public void update(long id, String name, String email, String password, Date birthday, byte age, Sex sex, String phone, Role role);
	
	/**
	 *  Deletes the object user with the specified id
	 *  id - id of the object user which will deleted
	 */
	public void delete(long id);
	
	/**
	 *  Returns the object user with the specified id
	 *  id - id of the object user which will returned
	 */
	public User findById(long id);
	
	/**
	 *  Returns list of the objects user with the specified name
	 */
	public List<User> findByName(String name);
	
	/**
	 *  Returns the list of the objects user which contain the specified object role
	 */
	public List<User> findByRole(Role role);
	
	/**
	 *  Returns the list of all objects user which are contained in the table User
	 */
	public List<User> getAll();

}