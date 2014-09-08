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
		ob.start();
	}

	/**
	 * Starts an application
	 */
	private void start() {
		createServiseObjects();

		//save("User");
		//update("User");
		//delete("User");
		//findById("User");
		
		//save("Favorite");
		//update("Favorite");
		//delete("Favorite");
		//findById("Favorite");
		//getAll("Favorite");
		
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
	 * Saves the data into the tables of SoftTourDatabase
	 * @param tableName - name of the table where the data will saved 
	 */
	private void save(String tableName) {
		
		switch (tableName) {
		case "User":
			userService.save("Kate", "ka@com", "1111", Date.valueOf("2014-08-11"), (byte)16, Sex.FEMALE, "123456", roleService.findById(2));
			break;
		case "Favorite":
			favoriteService.save(Date.valueOf("2014-08-10"), userService.findById(1), tourService.findById(1));
			break;
		case "Country":
			
			break;
		case "Feedback":
			
			break;
		case "Food":
			
			break;
		case "HistoryRecords":
			
			break;
		case "HistoryRequest":
			
			break;
		case "Hotel":
			
			break;
		case "Region":
			
			break;
		case "Role":
			
			break;
		case "Tour":
			
			break;
		default:
			System.out.println("No table with the specified name ! \n Please enter the corect table name !");
		}
		
	}
	
	/**
	 * Updates the data into the tables of SoftTourDatabase
	 * @param tableName - name of the table where the data will updated
	 */
	private void update(String tableName) {
		
		switch (tableName) {
		case "User":
			userService.update(3, "Ivan", "iv@com", "1111", Date.valueOf("2014-09-11"), (byte)16, Sex.MALE, "123456", roleService.findById(2));
			break;
		case "Favorite":
			favoriteService.update(9, Date.valueOf("2014-09-10"), userService.findById(1), tourService.findById(1));
			break;
		case "Country":
			
			break;
		case "Feedback":
			
			break;
		case "Food":
			
			break;
		case "HistoryRecords":
			
			break;
		case "HistoryRequest":
			
			break;
		case "Hotel":
			
			break;
		case "Region":
			
			break;
		case "Role":
			
			break;
		case "Tour":
			
			break;
		default:
			System.out.println("No table with the specified name ! \n Please enter the corect table name !");
		}
	}
	
	/**
	 * Deletes the data into the tables of SoftTourDatabase
	 * @param tableName - name of the table where the data will deleted 
	 */
	private void delete(String tableName) {
		switch (tableName) {
		case "User":
			userService.delete(3);
			break;
		case "Favorite":
			favoriteService.delete(10);
			break;
		case "Country":
			
			break;
		case "Feedback":
			
			break;
		case "Food":
			
			break;
		case "HistoryRecords":
			
			break;
		case "HistoryRequest":
			
			break;
		case "Hotel":
			
			break;
		case "Region":
			
			break;
		case "Role":
			
			break;
		case "Tour":
			
			break;
		default:
			System.out.println("No table with the specified name ! \n Please enter the corect table name !");
		}
	}

	/**
	 *  Returns the object with the specified id
	 * @param tableName - name of the table where the data will looked for
	 */
	private void findById(String tableName) {
		switch (tableName) {
		case "User":
		User user = userService.findById(2);
		System.out.println(user.getName());
			break;
		case "Favorite":
		Favorite favorite = favoriteService.findById(9);
		System.out.println(favorite.getDate());
			break;
		case "Country":
			
			break;
		case "Feedback":
			
			break;
		case "Food":
			
			break;
		case "HistoryRecords":
			
			break;
		case "HistoryRequest":
			
			break;
		case "Hotel":
			
			break;
		case "Region":
			
			break;
		case "Role":
			
			break;
		case "Tour":
			
			break;
		default:
			System.out.println("No table with the specified name ! \n Please enter the corect table name !");
		}
	}

	/**
	 *  Returns the list of all objects which are contained in the tableName
	 * @param tableName - name of the table where the data will looked for
	 */
	private void getAll(String tableName) {
		switch (tableName) {
		case "User":
			List<User> list = userService.getAll();
			showList(list);
			break;
		case "Favorite":
			List<Favorite> list1 = favoriteService.getAll();
			showList(list1);
			break;
		case "Country":
			
			break;
		case "Feedback":
			
			break;
		case "Food":
			
			break;
		case "HistoryRecords":
			
			break;
		case "HistoryRequest":
			
			break;
		case "Hotel":
			
			break;
		case "Region":
			
			break;
		case "Role":
			
			break;
		case "Tour":
			
			break;
		default:
			System.out.println("No table with the specified name ! \n Please enter the corect table name !");
		}
	}

	/**
	 * Shows the list of all objects which are contained in the given list
	 * @param list - list of objects which will be showon
	 */
	private void showList(List<?> list) {
		
		// We need to override the toString methods for all entity clases !!!
		
		for (Object object : list) {
			System.out.println(object);
		}
	} 
}