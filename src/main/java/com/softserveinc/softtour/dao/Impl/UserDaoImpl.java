package com.softserveinc.softtour.dao.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.softserveinc.softtour.dao.UserDao;
import com.softserveinc.softtour.entity.Role;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.entity.template.Sex;

/**
 * @author Andriy
 * 	Contains the methods for work with table User in the SoftTour database
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	
	/**
	 * Saves the object user to the table User
	 */
	@Override
	public void save(String name, String email, String password, Date birthday,
			byte age, Sex sex, String phone, Role role) {
		User user = new User(name, email, password, birthday, age, sex, phone, role);
		getHibernateTemplate().save(user);
	}

	/**
	 *  Updates the object user with the specified id
	 *  id - id of the object user which will updated
	 */
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

	/**
	 *  Deletes the object user with the specified id
	 *  id - id of the object user which will deleted
	 */
	@Override
	public void delete(long id) {
		User user = (User) getHibernateTemplate().get(User.class, id);
		if (user != null) {
			getHibernateTemplate().delete(user);
		} else {
			System.err.println("Error ! \n No user with this ID !");
		}
	}

	/**
	 *  Returns the object user with the specified id
	 *  id - id of the object user which will returned
	 */
	@Override
	public User findById(long id) {
		User user = (User) getHibernateTemplate().get(User.class, id);
		
		return user;
	}
	
	/**
	 *  Returns list of the objects user with the specified name
	 */
	@Override
	public List<User> findByName(String name) {
		String queryFindByName = "From User Where name = ?"; 
		@SuppressWarnings("unchecked")
		List<User> list = getHibernateTemplate().find(queryFindByName, name);
		
		return list;
	}
	
	/**
	 *  Returns the list of the objects user which contain the specified object role
	 */
	@Override
	public List<User> findByRole(Role role) {
		String queryFindByRole = "From User Where role_id = ?"; 
		@SuppressWarnings("unchecked")
		List<User> list = getHibernateTemplate().find(queryFindByRole, role.getId());
		
		return list;
	}

	/**
	 *  Returns the list of all objects user which are contained in the table User
	 */
	@Override
	public List<User> getAll() {
		String queryGetAll = "From User";
		@SuppressWarnings("unchecked")
		List<User> list = getHibernateTemplate().find(queryGetAll);
		
		return list;
	}

}