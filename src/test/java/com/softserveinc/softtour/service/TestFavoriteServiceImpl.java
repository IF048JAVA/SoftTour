package com.softserveinc.softtour.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Region;
import com.softserveinc.softtour.entity.Role;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.entity.template.Food;
import com.softserveinc.softtour.entity.template.RoomType;
import com.softserveinc.softtour.entity.template.Sex;
import com.softserveinc.softtour.repository.HotelRepository;

@Test
@ContextConfiguration(locations = {"/spring-test-config.xml", 
									"/spring-data.xml",
									"/applicationContext.xml",
									"/mvc-dispatcher-servlet.xml",
									"/spring-security.xml",
									"/spring-mail.xml"})
@WebAppConfiguration
public class TestFavoriteServiceImpl extends AbstractTestNGSpringContextTests {
	@Autowired
	private FavoriteService favoriteService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private RegionService regionService;
	
	@Autowired
	private HotelService hotelService;
	
    @Autowired
    private HotelRepository hotelRepository;
    
    @Autowired
    private TourService tourService;
    
	private Favorite favorite;
	private User user;
	private Country country;
	private Region region;
	private Hotel hotel;
	private Tour tour;

	String timestamp;
	
	@BeforeClass
	private void createFavorite() {
		timestamp = String.valueOf(System.currentTimeMillis());
		
		createUser();
		createCountry();
		createRegion();
		createHotel();
		createTour();
		
		favorite = new Favorite(Date.valueOf("2014-11-10"), user, tour);
	}

	private void createUser() {
		Role role = roleService.findByName("ROLE_USER");
		
		user = new User("user" + timestamp, "email" +timestamp + "@gmail.com", 
				"testPassword", Date.valueOf("2000-06-02"), Sex.MALE, "09976644433", role);
		userService.save(user);
		//Set id for this user
		user = userService.findByEmail(user.getEmail());
	}
	
	private void createCountry() {
		country = new Country("country" + timestamp, (long)5555);
		countryService.save(country);
		//Set id for this country
		country = countryService.findByName(country.getName());
	}

	private void createRegion() {
		region = new Region("region" + timestamp, (long)5359, country);
		regionService.save(region);
		//Set id for this region
		region = regionService.findByName(region.getName());
	}
	
	private void createHotel() {
		hotel = new Hotel("hotel" + timestamp, 3, 4, null, null, null, null, null, "", (long)5359, region);
		hotelService.save(hotel);
		//Set id for this hotel
		hotel = hotelService.findByName(hotel.getName());
	}
	
	private void createTour() {
		tour = new Tour(Date.valueOf("2000-06-02"), 7, "Київ", Time.valueOf("11:30:00"), BigDecimal.valueOf(242.55), hotel, Food.AI);
		tour.setRoomType(RoomType.STD);
		tourService.save(tour);
	}
	
	@Test
	public void testSave() {
		favoriteService.save(favorite);
		List<Favorite> list = favoriteService.findByUser(user);
		
		Favorite actualFavorite = list.get(0); 
		favorite.setId(actualFavorite.getId());
		
		assertNotNull(actualFavorite);
		assertEquals(actualFavorite, favorite);
	}
	
	@Test(dependsOnMethods={"testSave"})
	public void testFindById() {
		Favorite actualFavorite = favoriteService.findById(favorite.getId());
		
		assertNotNull(actualFavorite);
		assertEquals(actualFavorite, favorite);
	}

	@Test(dependsOnMethods={"testFindById"})
	public void testFindByUser() {
		List<Favorite> list = favoriteService.findByUser(user);
		
		Favorite actualFavorite = list.get(0); 
		favorite.setId(actualFavorite.getId());
		
		assertNotNull(actualFavorite);
		assertEquals(actualFavorite, favorite);
	}
	
	@Test(dependsOnMethods={"testFindByUser"})
	public void testFindByUserAndTour() {
		Favorite actualFavorite = null;
		actualFavorite = favoriteService.findByUserAndTour(user, tour);
		
		assertNotNull(actualFavorite);
		assertEquals(actualFavorite, favorite);
		
		actualFavorite = favoriteService.findByUserAndTour(user, null);
		assertNull(actualFavorite);
		
		actualFavorite = favoriteService.findByUserAndTour(null, tour);
		assertNull(actualFavorite);
	}
	
	@Test(dependsOnMethods = {"testFindByUserAndTour"})
	public void testDelete() {
		favoriteService.delete(favorite.getId());
		Favorite actualFavorite = favoriteService.findById(favorite.getId());

		assertNull(actualFavorite);
	}
	
	//TODO !!!
	@AfterClass
	private void deleteTestData(){
		tourService.delete(tour);
		
		//FIXME  Need to create delete method into HotelService
		hotelRepository.delete(hotel.getId());
		
		regionService.deleteById(region.getId());
		countryService.delete(country.getId());
		userService.delete(user.getId());
	}

}