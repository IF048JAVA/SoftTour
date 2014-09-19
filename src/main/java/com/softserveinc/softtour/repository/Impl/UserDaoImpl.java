package com.softserveinc.softtour.repository.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.softserveinc.softtour.repository.UserRepository;
import com.softserveinc.softtour.entity.Role;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.entity.template.Sex;

/**
 * @author Andriy
 * 	Contains the methods for work with table User in the SoftTour database
 */
public class UserDaoImpl extends HibernateDaoSupport {
	
	/**
	 * Saves the object user to the table User
	 */
	public void save(String name, String email, String password, Date birthday,
			byte age, Sex sex, String phone, Role role) {
		User user = new User(name, email, password, birthday, age, sex, phone, role);
		getHibernateTemplate().save(user);
	}

	/**
	 *  Updates the object user with the specified id
	 *  id - id of the object user which will updated
	 */
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
	public User findById(long id) {
		User user = (User) getHibernateTemplate().get(User.class, id);
		
		return user;
	}
	
	/**
	 *  Returns list of the objects user with the specified name or names
	 */
	public List<User> findByName(String...name) {
		String queryFindByName = createQuery("name", name.length);
		
		List<User> list = getHibernateTemplate().find(queryFindByName, name);
		return list;
	}
	
	/**
	 *  Returns list of the objects user with the specified email or emails
	 */
	public List<User> findByEmail(String... email) {
		String queryFindByEmail = createQuery("email", email.length);
		
		List<User> list = getHibernateTemplate().find(queryFindByEmail, email);
		return list;
	}
	
	/**
	 *  Returns list of the objects user with the specified password or passwords
	 */
	public List<User> findByPassword(String... password) {
		String queryFindByPassword = createQuery("password", password.length);
		
		List<User> list = getHibernateTemplate().find(queryFindByPassword, password);
		return list;
	}
	
	/**
	 *  Returns list of the objects user with the specified birthday or birthdays
	 */
	public List<User> findByBirthday(Date... birthday) {
		String queryFindByBirthday = createQuery("birthday", birthday.length);
		
		List<User> list = getHibernateTemplate().find(queryFindByBirthday, birthday);
		return list;
	}
	
	/**
	 *  Returns list of the objects user with the specified age
	 */
	public List<User> findByAge(Byte... age) {
		String queryFindByAge = createQuery("age", age.length);
		
		List<User> list = getHibernateTemplate().find(queryFindByAge, age);
		return list;
	}
	
	/**
	 *  Returns list of the objects user with the specified sex
	 */
	public List<User> findBySex(Sex sex) {
		String queryFindByAge = "FROM User WHERE sex = ?";
		
		List<User> list = getHibernateTemplate().find(queryFindByAge, sex);
		return list;
	}
	
	/**
	 *  Returns list of the objects user with the specified phone or phones
	 */
	public List<User> findByPhone(String...phone) {
		String queryFindByPhone = createQuery("phone", phone.length);
		
		List<User> list = getHibernateTemplate().find(queryFindByPhone, phone);
		return list;
	}
	
	/**
	 *  Returns the list of the objects user which contain the specified object or objects role
	 */
	public List<User> findByRole(Role...role) {
		String queryFindByRole = createQuery("role_id", role.length);
		
		List<User> list = getHibernateTemplate().find(queryFindByRole, role);
		return list;
	}

	/**
	 *  Returns the list of all objects user which are contained in the table User
	 */
	public List<User> getAll() {
		String queryGetAll = "From User";
		@SuppressWarnings("unchecked")
		List<User> list = getHibernateTemplate().find(queryGetAll);
		
		return list;
	}

	/**
	 * Creates the query for finding objects 
	 * @param columnName - name of the colunm which we write into condition WHERE
	 * @param length - length of vararg's elements
	 * @return query for finding objects 
	 */
	private String createQuery(String columnName, int length) {
		StringBuilder queryStringBuilder = new StringBuilder("FROM User WHERE ");
		
		for (int i = 0; i < length; i++) {
			queryStringBuilder.append(columnName + " = ? OR ");
		}
		queryStringBuilder.delete(queryStringBuilder.length()-4, queryStringBuilder.length());
		String queryString = queryStringBuilder.toString();
		return queryString;
	}

}