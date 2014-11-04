package com.softserveinc.softtour.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.entity.template.Sex;

/**
 * Tests the UserServiceImpl class
 * @author Andrii
 */
@Test
@ContextConfiguration(locations = {"/spring-test-config.xml", 
									"/spring-data.xml",
									"/applicationContext.xml",
									"/mvc-dispatcher-servlet.xml",
									"/spring-security.xml",
									"/spring-mail.xml"})
@WebAppConfiguration
public class TestUserServiceImpl extends AbstractTestNGSpringContextTests {
	private String timestamp;
	
	private User newUser1;
	private User newUser2;
	private User updatedUser;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * Creates new users for testing
	 */
	@BeforeClass 
	private void createNewUsers(){
		timestamp = String.valueOf(System.currentTimeMillis());
		newUser1 = createUser("User1" + timestamp, "email1" +timestamp + "@gmail.com", 
				"testPassword", "2000-06-02", Sex.MALE, "09976644433");
		newUser2 = createUser("User2" + timestamp, "email2" +timestamp + "@gmail.com", 
				"testPassword", "2000-06-02", Sex.MALE, "09976644433");
		
		updatedUser = createUser("User3" + timestamp, "email3" +timestamp + "@gmail.com", 
				"testPassword", "2000-06-02", Sex.FEMALE, "09976644433");
	}
		
	/**
	 * Creates new user with the specify parameters
	 * @return new user
	 */
	private User createUser(String name, String email, String password, String birthday, Sex sex, String phone) {
		return new User(name, email, password, Date.valueOf(birthday), sex, phone, roleService.findByName("ROLE_USER"));
	}
	
	/**
	 * Tests the method save of the UserServiceImpl class
	 */
	@Test
	public void testSave() {
		// Saving user
		userService.save(newUser1);

		// Retrieving user from database
		User actualUser = userService.findByEmail(newUser1.getEmail());
		newUser1.setId(actualUser.getId());
		
		assertNotNull(actualUser);
		assertEquals(actualUser, newUser1);
	}
	
	/**
	 * Tests the method findById of the UserServiceImpl class
	 */
	@Test(dependsOnMethods = {"testSave"})
	public void testFindById() {
		User actualUser = userService.findById(newUser1.getId()); 
		
		assertNotNull(actualUser);
		assertEquals(actualUser, newUser1);
	}
	
	/**
	 * Tests the method findByEmail of the UserServiceImpl class
	 */
	@Test(dependsOnMethods = {"testFindById"})
	public void testFindByEmail() {
		User actualUser = userService.findByEmail(newUser1.getEmail());
		
		assertNotNull(actualUser);
		assertEquals(actualUser, newUser1);
	}
		
	/**
	 * Tests the method findByNameOrEmail of the UserServiceImpl class
	 */
	@Test(dependsOnMethods = {"testFindByEmail"})
	public void testFindByNameOrEmail() {
		List<User> list = null;
		User actualUser = null;
		
		list = userService.findByNameOrEmail(newUser1.getName(), null);
		actualUser = list.get(0);
		
		assertNotNull(actualUser);
		assertEquals(actualUser, newUser1);

		list = userService.findByNameOrEmail(null, newUser1.getEmail());
		actualUser = list.get(0);
		
		assertNotNull(actualUser);
		assertEquals(actualUser, newUser1);
		
		userService.save(newUser2);
		newUser2 = userService.findByEmail(newUser2.getEmail());
		
		list = userService.findByNameOrEmail(newUser1.getName(), newUser2.getEmail());

		assertEquals(list.size(), 2);
		assertTrue(list.contains(newUser1));
		assertTrue(list.contains(newUser2));
	}

	/**
	 * Tests the method update of the UserServiceImpl class
	 */
	@Test(dependsOnMethods = {"testFindByNameOrEmail"})
	public void testUpdate() {
		long id = newUser1.getId();
		
		userService.update(id, updatedUser);
		updatedUser.setId(id);
		
		User actualUser = userService.findById(id); 
		
		assertNotNull(actualUser);
		assertEquals(actualUser, updatedUser);
	}
	
	/**
	 * Tests the method delete of the UserServiceImpl class
	 */
	@Test(dependsOnMethods = {"testUpdate"})
	public void testDelete() {
		User actualUser = null;
		
		userService.delete(updatedUser.getId());
		userService.delete(newUser2.getId());
	
		actualUser = userService.findById(updatedUser.getId());
		assertNull(actualUser);
		
		actualUser = userService.findById(newUser2.getId());
		assertNull(actualUser);
	}
}
