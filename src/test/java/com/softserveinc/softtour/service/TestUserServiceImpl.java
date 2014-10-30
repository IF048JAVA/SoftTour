/*package com.softserveinc.softtour.service;

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

@Test
@ContextConfiguration(locations = {"/spring-test-config.xml", 
									"/WEB-INF/spring-data.xml",
									"/WEB-INF/applicationContext.xml",
									"/WEB-INF/mvc-dispatcher-servlet.xml",
									"/WEB-INF/spring-security.xml",
									"/WEB-INF/spring-mail.xml"})
@WebAppConfiguration
public class TestUserServiceImpl extends AbstractTestNGSpringContextTests {
	private static final String ROLE_USER = "ROLE_USER";
	private String timestamp;
	
	private User newUser;
	private User updatedUser;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@BeforeClass 
	private void createNewUser(){
		timestamp = String.valueOf(System.currentTimeMillis());
		newUser = createUser("User1" + timestamp, "email1" +timestamp + "@gmail.com", 
				"testPassword", "2000-06-02", Sex.MALE, "09976644433");
		updatedUser = createUser("User2" + timestamp, "email1" +timestamp + "@gmail.com", 
				"testPassword", "2000-06-02", Sex.FEMALE, "09976644433");
	}
		
	private User createUser(String name, String email, String password, String birthday, Sex sex, String phone) {
		return new User(name, email, password, Date.valueOf(birthday), sex, phone, roleService.findByName(ROLE_USER));
	}
	
	@Test
	public void testSave() {
		// Saving user
		userService.save(newUser);

		// Retrieving user from database
		User actualUser = userService.findByEmail(newUser.getEmail());
		newUser.setId(actualUser.getId());
		
		assertNotNull(actualUser);
		assertEquals(actualUser, newUser);
	}
	
	@Test(dependsOnMethods = {"testSave"})
	public void testFindById() {
		User actualUser = userService.findById(newUser.getId()); 
		
		assertNotNull(actualUser);
		assertEquals(actualUser, newUser);
	}
	
	@Test(dependsOnMethods = {"testFindById"})
	public void testFindByEmail() {
		User actualUser = userService.findByEmail(newUser.getEmail());
		
		assertNotNull(actualUser);
		assertEquals(actualUser, newUser);
	}
		
	@Test(dependsOnMethods = {"testFindByEmail"})
	public void testFindByNameOrEmail() {
		List<User> list = null;
		User actualUser = null;
		
		list = userService.findByNameOrEmail(newUser.getName(), null);
		actualUser = list.get(0);
		
		assertNotNull(actualUser);
		assertEquals(actualUser, newUser);

		list = userService.findByNameOrEmail(null, newUser.getEmail());
		actualUser = list.get(0);
		
		assertNotNull(actualUser);
		assertEquals(actualUser, newUser);
		
//		userService.save(updatedUser);
//		
//		list = userService.findByNameOrEmail(newUser.getName(), updatedUser.getEmail());
//
//		assertEquals(list.size(), 2);
//		assertTrue(list.contains(newUser));
//		assertTrue(list.contains(updatedUser));
//		
//		userService.delete(id);
	}

	@Test(dependsOnMethods = {"testFindByNameOrEmail"})
	public void testUpdate() {
		long id = newUser.getId();
		
		userService.update(id, updatedUser);
		updatedUser.setId(id);
		
		User actualUser = userService.findById(id); 
		
		assertNotNull(actualUser);
		assertEquals(actualUser, updatedUser);
	}
	
	@Test(dependsOnMethods = {"testUpdate"})
	public void testDelete() {
		userService.delete(updatedUser.getId());
		
		User actualUser = userService.findById(updatedUser.getId());

		assertNull(actualUser);
	}
}
*/