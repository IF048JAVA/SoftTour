package com.softserveinc.softtour.dao;

import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.entity.template.Sex;

import java.sql.Date;
import java.util.List;

public interface UserDao {
	
	public void save(String name, String e_mail, String password, Date birthday, byte age, Sex sex, String phone, long role_id);
	public void update(long id, String name, String e_mail, String password, Date birthday, byte age, Sex sex, String phone, long role_id);
	public void delete(long id);
	public User findById(long id);
	public List<User> getAll();
	
}
