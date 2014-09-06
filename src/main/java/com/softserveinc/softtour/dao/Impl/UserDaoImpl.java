package com.softserveinc.softtour.dao.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.softserveinc.softtour.dao.UserDao;
import com.softserveinc.softtour.entity.Role;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.entity.template.Sex;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	
	@Override
	public void save(String name, String email, String password, Date birthday,
			byte age, Sex sex, String phone, Role role) {
		User user = new User(name, email, password, birthday, age, sex, phone, role);
		getHibernateTemplate().save(user);
	}

	@Override
	public void update(long id, String name, String email, String password,
			Date birthday, byte age, Sex sex, String phone, Role role) {
		
		User user = (User) getHibernateTemplate().get(User.class, id);
		
		if (user != null) {
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setBirthday(birthday);
			user.setAge(age);
			user.setSex(sex);
			user.setPhone(phone);
			user.setRole(role);
			
			getHibernateTemplate().update(user);
		} else {
			System.err.println("Error ! \n No user with this ID !");
		}
	}

	@Override
	public void delete(long id) {
		User user = (User) getHibernateTemplate().get(User.class, id);
		if (user != null) {
			getHibernateTemplate().delete(user);
		} else {
			System.err.println("Error ! \n No user with this ID !");
		}
	}

	@Override
	public User findById(long id) {
		User user = (User) getHibernateTemplate().get(User.class, id);
		
		return user;
	}
	
	@Override
	public List<User> findByName(String name) {
		String queryFindByName = "From User Where name = ?"; 
		@SuppressWarnings("unchecked")
		List<User> list = getHibernateTemplate().find(queryFindByName, name);
		
		return list;
	}
	
	@Override
	public List<User> findByRole(Role role) {
		String queryFindByRole = "From User Where role_id = ?"; 
		@SuppressWarnings("unchecked")
		List<User> list = getHibernateTemplate().find(queryFindByRole, role.getId());
		
		return list;
	}

	@Override
	public List<User> getAll() {
		String queryGetAll = "From User";
		@SuppressWarnings("unchecked")
		List<User> list = getHibernateTemplate().find(queryGetAll);
		
		return list;
	}

}