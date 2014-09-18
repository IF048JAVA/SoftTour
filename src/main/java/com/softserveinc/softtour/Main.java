package com.softserveinc.softtour;

import java.sql.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.softserveinc.softtour.entity.Favorite;
import com.softserveinc.softtour.entity.User;
import com.softserveinc.softtour.entity.template.Sex;
import com.softserveinc.softtour.service.CountryService;
import com.softserveinc.softtour.service.FavoriteService;
import com.softserveinc.softtour.service.FeedbackService;
import com.softserveinc.softtour.service.FoodService;
import com.softserveinc.softtour.service.HistoryRecordsService;
import com.softserveinc.softtour.service.HistoryRequestService;
import com.softserveinc.softtour.service.HotelService;
import com.softserveinc.softtour.service.RegionService;
import com.softserveinc.softtour.service.RoleService;
import com.softserveinc.softtour.service.TourService;
import com.softserveinc.softtour.service.UserService;

/**
 * @author Andriy
 * It is test class which performs various operations with database
 */
public class Main {

	private ApplicationContext context;
	
	private UserService userService; 
	private RoleService roleService;
	private HotelService hotelService;
	private HistoryRequestService historyRequestService;
	private HistoryRecordsService historyRecordsService;
	private FavoriteService favoriteService;
	private FeedbackService feedbackService;
	private CountryService countryService;
	private RegionService regionService;
	private TourService tourService;
	private FoodService foodService;
	
	/**
	 * Creates the context for this application
	 */
	public Main() {
		context = new ClassPathXmlApplicationContext("spring/config.xml");
	}
	
	/**
	 * Entry point for applications 
	 */
	public static void main(String[] args) {
		Main ob = new Main();
		ob.createServiseObjects();
		ob.start();
	}

	/**
	 * Creates the objects of the classes which are services of this appliaction 
	 */
	private void createServiseObjects() {
		userService = (UserService) context.getBean("userService");
		roleService = (RoleService) context.getBean("roleService");
		historyRecordsService = (HistoryRecordsService) context.getBean("historyRecordsService");
		historyRequestService = (HistoryRequestService) context.getBean("historyRequestService");
		feedbackService = (FeedbackService) context.getBean("feedbackService");
		favoriteService = (FavoriteService) context.getBean("favoriteService");
		hotelService = (HotelService) context.getBean("hotelService");
		regionService = (RegionService) context.getBean("regionService");
		countryService = (CountryService) context.getBean("countryService");
		tourService = (TourService) context.getBean("tourService");
		foodService = (FoodService) context.getBean("foodService");
	}
	
	/**
	 * Starts an test application
	 */
	private void start() {
		
		//testUser();
		//testFavorite();
		
	}

	private void testFavorite() {
		//favoriteService.save(Date.valueOf("2014-08-10"), userService.findById(1), tourService.findById(1));
		//favoriteService.update(9, Date.valueOf("2014-09-10"), userService.findById(1), tourService.findById(1));
		//favoriteService.delete(10);
		//User user = userService.findById(2);
		//System.out.println(user.getName());
		//List<Favorite> list1 = favoriteService.getAll();
		
		//List<Favorite> list = favoriteService.findByDate(Date.valueOf("2014-09-04"), Date.valueOf("2014-09-10"));
		//List<Favorite> list = favoriteService.findByUser(userService.findById(1), userService.findById(2));
		//List<Favorite> list = favoriteService.findByTour(tourService.findById(1), tourService.findById(2));
		
		//showList(list);
	}

	private void testUser() {
		userService.save("Pink", "try@com", "1234", Date.valueOf("1979-09-08"), (byte)35, Sex.FEMALE, "775511", roleService.findById(1));
		//userService.update(3, "Ivan", "iv@com", "1111", Date.valueOf("2014-09-11"), (byte)16, Sex.MALE, "123456", roleService.findById(2));
		//userService.delete(3);
		//User user = userService.findById(2);
		//System.out.println(user);
		//List<User> list = userService.getAll();
		
		//List<User> list = userService.findByName("Andriy", "Kate");
		//List<User> list = userService.findByEmail("iv@com", "elips@gmail.com");
		//List<User> list = userService.findByPassword("1111", "2222");
		//List<User> list = userService.findByBirthday(Date.valueOf("1990-06-12"), Date.valueOf("1987-11-24"));
		//List<User> list = userService.findByAge((byte)16, (byte)26);
		//List<User> list = userService.findBySex(Sex.MALE);
		//List<User> list = userService.findByPhone("123456", "+380958000877");
		//List<User> list = userService.findByRole(roleService.findById(2), roleService.findById(3));

		//showList(list);		
	}

	/**
	 * Shows the list of all objects which are contained in the given list
	 * @param list - list of objects which will be showon
	 */
	private void showList(List<?> list) {
		for (Object object : list) {
			System.out.println(object);
		}
	} 
	
}