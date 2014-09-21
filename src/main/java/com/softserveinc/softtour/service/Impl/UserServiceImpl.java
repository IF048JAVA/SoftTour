package com.softserveinc.softtour.service.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.softtour.repository.UserRepository;
import com.softserveinc.softtour.entity.Role;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.entity.template.Sex;
import com.softserveinc.softtour.service.UserService;

/**
 * @author Andriy
 * 	Contains the methods for work with table User in the SoftTour database
 *  Supports a transaction
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Saves the object user to the table User
	 * Supports a transaction
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void save(User user) {
		userRepository.save(user);
	}
	
	/**
	 * Updates the object user with the specified id
	 * @param id - id of the object user which will be updated
	 * @param user - it's the object with the new values
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void update(long id,User user) {
		userRepository.update( id, user.getName(), user.getEmail(), user.getPassword(), 
				user.getBirthday(), user.getAge(), user.getSex(), user.getPhone(), user.getRole());
	}

	/**
	 *  Deletes the object user with the specified id
	 *  id - id of the object user which will be deleted
	 *  Supports a transaction
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void delete(long id) {
		userRepository.delete(id);
	}

	/**
	 *  Returns the object user with the specified id
	 *  id - id of the object user which will be returned
	 *  Supports a transaction
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public User findById(long id) {
		return userRepository.findOne(id);
	}
	
	/**
	 *  Returns the list of the user's objects with the specified parameters
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<User> findByAnyParameters(long id, String name, String email, String password, 
								Date birthday, byte age, Sex sex, String phone, Role role) {
		return userRepository.findByIdOrNameOrEmailOrPasswordOrBirthdayOrAgeOrSexOrPhoneOrRole(
				id, name, email, password, birthday, age, sex, phone, role);
	}
	
	/**
	 *  Returns the list of all user's objects which are contained in the table User
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
