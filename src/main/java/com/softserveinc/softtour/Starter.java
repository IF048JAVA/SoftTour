package com.softserveinc.softtour;

import com.softserveinc.softtour.dto.BusTransit;
import com.softserveinc.softtour.parsers.BusParser;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by oleksandrgasenuk on 08.09.14.
 */
public class Starter {
    /*
    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/config.xml");
    private static CountryService countryService = (CountryService) applicationContext.getBean("countryService");
    private static FavoriteService favoriteService = (FavoriteService) applicationContext.getBean("favoriteService");
    private static FeedbackService feedbackService = (FeedbackService) applicationContext.getBean("feedbackService");
    private static FoodService foodService = (FoodService) applicationContext.getBean("foodService");
    private static HistoryRecordsService historyRecordsService = (HistoryRecordsService) applicationContext.getBean("historyRecordsService");
    private static HistoryRequestService historyRequestService = (HistoryRequestService) applicationContext.getBean("historyRequestService");
    private static HotelService hotelService = (HotelService) applicationContext.getBean("hotelService");
    private static RegionService regionService = (RegionService) applicationContext.getBean("regionService");
    private static RoleService roleService = (RoleService) applicationContext.getBean("roleService");
    private static TourService tourService = (TourService) applicationContext.getBean("tourService");
    private static UserService userService = (UserService) applicationContext.getBean("userService");
    */
    public static void main(String[] args) {
        gashenyukTestingMethod();

    }

    public static void gashenyukTestingMethod() {
        /*
        countryService.save("USA");
        countryService.save("Ukraine");
        countryService.save("UAE");

        regionService.save("California", countryService.findById(4));
        regionService.save("Morshin", countryService.findById(5));
        regionService.save("Sharmelsheih", countryService.findById(6));

        hotelService.save("California", 3, regionService.findById(5));
        hotelService.save("Diamant", 4, regionService.findById(6));
        hotelService.save("Al-Jazira", 5, regionService.findById(7));

        tourService.save(new GregorianCalendar(12, 8, 2014).getTime(), 10, "Старі Богородчани",
                new GregorianCalendar(11, 8, 2014).getTime(), new BigDecimal(500.00), hotelService.findById(6),
                foodService.findById(5));
        List<Hotel> hotelsList = hotelService.findByStars(3, 5);
        for (Hotel hotel : hotelsList) {
            System.out.println(hotel.getName());
        }
        */
        BusParser busParser = new BusParser();
        List<BusTransit> busList = null;
        try {
            busList = busParser.parse("Київ", "Львів", new GregorianCalendar(2014, 8, 24, 15, 30).getTime());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(BusTransit bus : busList){
            System.out.println(bus);
        }
    }
}
