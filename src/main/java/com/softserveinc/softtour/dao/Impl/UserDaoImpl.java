package com.softserveinc.softtour.dao.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.softserveinc.softtour.dao.UserDao;
import com.softserveinc.softtour.dto.Sex;
import com.softserveinc.softtour.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public void save(String name, String e_mail, String password,
			Date birthday, byte age, Sex sex, String phone, long role_id) {
		User user = new User(name, e_mail, password, birthday, age, sex, phone, role_id);
		getHibernateTemplate().save(user);
	}

	@Override
	public void update(long id, String name, String e_mail, String password,
			Date birthday, byte age, Sex sex, String phone, long role_id) {
		
		User user = (User) getHibernateTemplate().get(User.class, id);
		
		if (user != null) {
			user.setName(name);
			user.setE_mail(e_mail);
			user.setPassword(password);
			user.setBirthday(birthday);
			user.setAge(age);
			user.setSex(sex);
			user.setPhone(phone);
			user.setRole_id(role_id);
			
			getHibernateTemplate().update(user);
		} else {
			System.err.println("Error ! \n User is null !");
		}
	}

	@Override
	public void delete(long id) {
		User user = (User) getHibernateTemplate().get(User.class, id);
		if (user != null) {
			getHibernateTemplate().delete(user);
		} else {
			System.err.println("Error ! \n User is null !");
		}
	}

	@Override
	public User findById(long id) {
		User user = (User) getHibernateTemplate().get(User.class, id);
		return user;
	}

	@Override
	public List<User> getAll() {
		String queryGetAll = "From User";
		@SuppressWarnings("unchecked")
		List<User> list = getHibernateTemplate().find(queryGetAll);
		
		return list;
	}

}