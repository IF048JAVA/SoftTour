package com.softserveinc.softtour.service.Impl;

import java.sql.Date;
import java.util.List;

import com.softserveinc.softtour.dao.UserDao;
import com.softserveinc.softtour.dto.Sex;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void save(String name, String e_mail, String password,
			Date birthday, byte age, Sex sex, String phone, long role_id) {
		userDao.save(name, e_mail, password, birthday, age, sex, phone, role_id);
	}

	@Override
	public void update(long id, String name, String e_mail, String password,
			Date birthday, byte age, Sex sex, String phone, long role_id) {
		userDao.update(id, name, e_mail, password, birthday, age, sex, phone, role_id);
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
