package com.softserveinc.softtour.service.Impl;

import java.sql.Date;
import java.util.List;

import com.softserveinc.softtour.dao.UserDao;
import com.softserveinc.softtour.entity.Role;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.entity.template.Sex;
import com.softserveinc.softtour.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void save(String name, String email, String password,
			Date birthday, byte age, Sex sex, String phone, Role role) {
		userDao.save(name, email, password, birthday, age, sex, phone, role);
	}

	@Override
	public void update(long id, String name, String email, String password,
			Date birthday, byte age, Sex sex, String phone, Role role) {

		userDao.update(id, name, email, password, birthday, age, sex, phone, role);
	}

	@Override
	public void delete(long id) {
		userDao.delete(id);
	}

	@Override
	public User findById(long id) {
		return userDao.findById(id);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

}
