package com.softserveinc.softtour.service;

import java.sql.Date;
import java.util.List;

import com.softserveinc.softtour.dto.Sex;
import com.softserveinc.softtour.entity.User;

public interface UserService {
	
	void save(String name, String e_mail, String password, Date birthday, byte age, Sex sex, String phone, long role_id);
	void update(long id, String name, String e_mail, String password, Date birthday, byte age, Sex sex, String phone, long role_id);
	void delete(long id);
	User findById(long id);
	List<User> getAll();
}
