package com.softserveinc.softtour.service;

import java.sql.Date;
import java.util.List;

import com.softserveinc.softtour.dto.Sex;
import com.softserveinc.softtour.entity.User;

public interface UserService {
	
	public void save(String name, String e_mail, String password, Date birthday, byte age, Sex sex, String phone, long role_id);
	public void update(long id, String name, String e_mail, String password, Date birthday, byte age, Sex sex, String phone, long role_id);
	public void delete(long id);
	public User findById(long id);
	public List<User> getAll();
}
