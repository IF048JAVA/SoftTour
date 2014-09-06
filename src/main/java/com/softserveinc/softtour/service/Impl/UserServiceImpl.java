package com.softserveinc.softtour.service.Impl;

import java.sql.Date;
import java.util.List;

import com.softserveinc.softtour.dao.UserDao;
import com.softserveinc.softtour.entity.Role;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.entity.template.Sex;
import com.softserveinc.softtour.service.UserService;

/**
 * @author Andriy
 * 	Contains the methods for work with table User in the SoftTour database
 */
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	/**
	 * Sets the userDao object
	 * @param userDao - object of the class UserDaoImpl
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * Saves the object user to the table User
	 */
	@Override
	public void save(String name, String email, String password, Date birthday,
			byte age, Sex sex, String phone, Role role) {
		userDao.save(name, email, password, birthday, age, sex, phone, role);
	}

	/**
	 *  Updates the object user with the specified id
	 *  id - id of the object user which will updated
	 */
	@Override
	public void update(long id, String name, String email, String password,
			Date birthday, byte age, Sex sex, String phone, Role role) {

		userDao.update(id, name, email, password, birthday, age, sex, phone,
				role);
	}

	/**
	 *  Deletes the object user with the specified id
	 *  id - id of the object user which will deleted
	 */
	@Override
	public void delete(long id) {
		userDao.delete(id);
	}

	/**
	 *  Returns the object user with the specified id
	 *  id - id of the object user which will returned
	 */
	@Override
	public User findById(long id) {
		return userDao.findById(id);
	}

	/**
	 *  Returns list of the objects user with the specified name
	 */
	@Override
	public List<User> findByName(String name) {
		return userDao.findByName(name);
	}

	/**
	 *  Returns the list of the objects user which contain the specified object role
	 */
	@Override
	public List<User> findByRole(Role role) {
		return userDao.findByRole(role);
	}
	
	/**
	 *  Returns the list of all objects user which are contained in the table User
	 */
	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

}
