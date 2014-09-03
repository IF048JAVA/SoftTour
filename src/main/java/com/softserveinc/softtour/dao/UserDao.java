package com.softserveinc.softtour.dao;

import java.sql.Date;
import java.util.List;

import com.softserveinc.softtour.entity.Role;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.entity.template.Sex;

public interface UserDao {
	
	public void save(String name, String email, String password, Date birthday, byte age, Sex sex, String phone, Role role);
	public void update(long id, String name, String email, String password, Date birthday, byte age, Sex sex, String phone, Role role);
	public void delete(long id);
	public User findById(long id);
	public List<User> getAll();
	
}
