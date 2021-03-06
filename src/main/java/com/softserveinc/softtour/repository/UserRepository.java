package com.softserveinc.softtour.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.softserveinc.softtour.entity.Role;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.entity.template.Sex;

/**
 *  @author Andriy
 * 	Contains the methods for work with table User in the SoftTour database
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * Updates the object user with the specified id
	 * @param id - id of the object user which will be updated
	 * @param name - updated name
	 * @param email - updated email
	 * @param password - updated password
	 * @param birthday - updated birthday
	 * @param sex - updated sex
	 * @param phone - updated phone
	 * @param role - updated role
	 */
	@Modifying
	@Query("UPDATE User SET name=?2, email=?3, password=?4, birthday=?5, sex=?6, phone=?7, role_id=?8 WHERE id=?1" )
	public void update(long id, String name, String email, String password, 
					Date birthday, Sex sex, String phone, Role role);

    /**
     * Returns the list of the user's objects with the specified email
     */
    public User findByEmail(String name);

	/**
	 * Returns the list of the user's objects with the specified name or email
	 */	
	public List<User> findByNameOrEmail(String name, String email);

}