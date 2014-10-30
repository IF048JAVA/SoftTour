package com.softserveinc.softtour.parsers;

import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Region;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.template.Food;
import com.softserveinc.softtour.entity.template.RoomType;
import com.softserveinc.softtour.parsers.constants.ItTourParserConstants;
import com.softserveinc.softtour.util.ItTourParserUrlGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.util.Date;

/**
 * This class find tours and supported information by input parameters.
 * The routes data source is site: http://www.ittour.com.ua
 * This class use:
 *     com.softserveinc.softtour.parsers.constants.ItTourParserConstants - contains used in this class constants
 *     com.softserveinc.softtour.entity.Tour - represent tour
 *     com.softserveinc.softtour.util.ItTourParserUrlGenerator - generate web-page's url, that contains tours.
 * Class contains three overloaded constructors for such pages:
 *     index page with quick search
 *     search page with advanced search
 *     hotels page with search by hotel
 */
public class ItTourParser implements ItTourParserConstants {

    /**
     * Parse results would be save in this list
     */
    private List<Tour> tourList;
    private String country;
    private int adults;
    private int children;
    private String url;
    private Properties departureCityVocabulary = new Properties();
    private Map<Tour, String[]> tourIdMap;

    /**
     * This constructor is for quick search
     * @param country - tour country
     * @param countryParam - tour country parameter(country code, one of the url parameter)
     * @param adults - count adults, that plan to go to tour
     * @param children - count children, that plan to go to tour
     * @param priceMin - minimum money value, that can be paid for tour
     * @param priceMax - maximum money value, that can be paid for tour
     * @param pageNumber - number of page, that would be parse.
     *         Parse connect to the page, that can contains 20, 50 or 100 tours (This value contains in constant:
     *         ITEMS_PER_PAGE_VALUE in com.softserveinc.softtour.util.constants.ItTourParserUrlGeneratorConstants).
     *
     *         Example: If pageNumber will be equals 1, this parser would connect to first page with result,
     *         required to input parameters. Number 2 - to second and so on.
     */
    public ItTourParser(String country, long countryParam, int adults, int children, int priceMin, int priceMax, int pageNumber) {
        this.tourList = new ArrayList<>();
        this.country = country;
        this.adults = adults;
        this.children = children;
        this.url = ItTourParserUrlGenerator.createQuickSearchUrl(countryParam, adults, children, priceMin, priceMax, pageNumber);
        tourIdMap = new HashMap<>();
    }

    /**
     * This constructor is for advanced search
     * @param country - tour country
     * @param countryParam - tour country parameter(country code, one of the url parameter)
     * @param regionParam - tour region parameter(region code, one of the url parameter)
     * @param hotelStars - count of hotel stars. Set, that must contains values from 2 to 5.
     * @param food - type of food. Set with types of food. Must contains such values: HB,BB,FB,AI,UAI,RO.
     * @param adults - count adults, that plan to go to tour
     * @param children - count children, that plan to go to tour
     * @param dateFrom - from this date tour would be search. Value must be in format: dd.MM.yy
     * @param dateTo - tour would be search to this date. Value must be in format: dd.MM.yy
     * @param nightsFrom - minimum count of tour nights. Value must be from 1 to 21.
     * @param nightsTo - maximum count of tour nights. Value must be from 1 to 21.
     * @param priceMin - minimum money value, that can be paid for tour
     * @param priceMax - maximum money value, that can be paid for tour
     * @param pageNumber - number of page, that would be parse.
     *         Parse connect to the page, that can contains 20, 50 or 100 tours (This value contains in constant:
     *         ITEMS_PER_PAGE_VALUE in com.softserveinc.softtour.util.constants.ItTourParserUrlGeneratorConstants).
     *
     *         Example: If pageNumber will be equals 1, this parser would connect to first page with result,
     *         required to input parameters. Number 2 - to second and so on.
     */
    public ItTourParser(String country, long countryParam, long regionParam, Set<Integer> hotelStars, Set<String> food, int adults,
            int children, String dateFrom, String dateTo, int nightsFrom, int nightsTo, int priceMin, int priceMax,
            int pageNumber) {
        this.tourList = new ArrayList<>();
        this.country = country;
        this.adults = adults;
        this.children = children;
        this.url = ItTourParserUrlGenerator.createAdvanceSearchUrl(countryParam, regionParam, hotelStars, food, adults, children, dateFrom,
            dateTo, nightsFrom, nightsTo, priceMin, priceMax, pageNumber);
        tourIdMap = new HashMap<>();
    }

    /**
     * This constructor is for search by hotel.
     * @param hotel - object hotel
     * @param pageNumber - number of page, that would be parse.
     *         Parse connect to the page, that can contains 20, 50 or 100 tours (This value contains in constant:
     *         ITEMS_PER_PAGE_VALUE in com.softserveinc.softtour.util.constants.ItTourParserUrlGeneratorConstants).
     *
     *         Example: If pageNumber will be equals 1, this parser would connect to first page with result,
     *         required to input parameters. Number 2 - to second and so on.
     */
    public ItTourParser(Hotel hotel, int pageNumber) {
        this.tourList = new ArrayList<>();
        this.country = hotel.getRegion().getCountry().getName();
        this.adults = DEFAULT_ADULTS_COUNT;
        this.children = DEFAULT_CHILDREN_COUNT;
        this.url = ItTourParserUrlGenerator.createSearchUrlByHotel(hotel, pageNumber);
        tourIdMap = new HashMap<>();
    }

    /**
     * This method create connection to the web-page with tours that required constructor parameters.
     *
     * Web-page with tours are encapsulated in Jsoup object Document.
     * Document will be generated by using private method connect.
     *
     * Private method loadDepartureCityProperties load properties file data.
     * This data contains ru-ua vocabulary of the departure cities.
     * Our application contains Ukrainian names of cities, when parsed site - Russian.
     * So there is the need to translate cities names.
     *
     * Methods addTours add tours to tourList.
     *
     * @return list of tours (represented by Tour class)
     */
    public List<Tour> parse() {
        Document document = connect(url);
        System.out.println(url);
        loadDepartureCityProperties();
        addTours(document);
        return tourList;
    }

    /**
     * @param url - web-page's url with tours
     * @return web-page, encapsulated in Jsoup object Document
     */
    private Document connect(String url){
        String doc = null;
        try {
            doc = Jsoup.connect(url).
                  timeout(CONNECTION_TIMEOUT).
                  ignoreContentType(true).
                  execute().
                  body();
        } catch (IOException e) {
            //TODO get tours from database
            e.printStackTrace();
        }

        /**
         * Page is unparseable. Removing one backslash solve this problem.
         */
        String tourPage = doc.replace("\\", "");
        Document document = Jsoup.parse(tourPage);
        return document;
    }

    /**
     * This method load properties file data, that contains ru-ua vocabulary of the departure cities.
     */
    private void loadDepartureCityProperties(){
        try(InputStream inputDepCity = this.getClass().getResourceAsStream(DEPARTURE_CITY_PROPERTIES_PATH);
            InputStreamReader reader = new InputStreamReader(inputDepCity, UTF_8)) {
            departureCityVocabulary.load(reader);
        }catch (IOException e){
            System.err.println("Can't find properties file: " + DEPARTURE_CITY_PROPERTIES_PATH);
        }
    }

    /**
     * This method get from document tours data, create Tour objects and add them to tourList
     * @param document - web-page, encapsulated in Jsoup object Document
     */
    private void addTours(Document document) {

        /**
         * This list contains rows from table in web-page with tours.
         * The data, that will be use, starts from third row. Last two rows haven't useful data.
         */
        List<Element> tableRowList = document.getElementsByTag(TAG_TR);
        for (int i = DATA_START_NUMBER; i < tableRowList.size() - TAL_DATA_NUMBER; i++) {

            /**
             * cellList contains such data:
             * [index]:[value]
             *    0   : Region
             *    1   : Hotel, contains link to hotel page
             *    2   : Room type
             *    3   : Tag div with departure city
             *    4   : Hotel stars
             *    5   : Food type
             *    6   : Nights count
             *    7   : Date Fly
             *    8   : Link with tour details
             *    9   : Nine tags span with tour price. Second - hrn, fifth - €, Eighth - $,
             */
            List<Element> tableCellList = new ArrayList<>();
            tableCellList.addAll(tableRowList.get(i).getElementsByClass(CLASS_ITT_TEXT_LEFT));
            tableCellList.addAll(tableRowList.get(i).getElementsByClass(CLASS_TEXT_CENTER));
            tableCellList.add(tableRowList.get(i).getElementsByClass(CLASS_TEXT_RIGHT).first());

            /**
             * Get tour data in String format
             */
            String tourDateSt = tableCellList.get(TABLE_CELL_TOUR_DATE).text();
            String tourNightsSt = tableCellList.get(TABLE_CELL_NIGHTS_COUNT).text();
            String tourDepartureCitySt = tableCellList.get(TABLE_CELL_DEPARTURE_CITY).
                                         getElementsByTag(TAG_DIV).first().text();
            String tourPriceSt = tableCellList.get(TABLE_CELL_TOUR_PRICE).getElementsByTag(TAG_SPAN).get(7).text();
            String hotelName = tableCellList.get(TABLE_CELL_HOTEL_NAME).text();
            String hotelStars = tableCellList.get(TABLE_CELL_HOTEL_STARS).text();
            String hotelRegion = tableCellList.get(TABLE_CELL_HOTEL_REGION).text();
            String tourFood = tableCellList.get(TABLE_CELL_TOUR_FOOD).text();
            String roomTypeSt = tableCellList.get(TABLE_CELL_TOUR_ROOM_TYPE).text().toUpperCase();

            /**
             * Convert tour data to Tour constructor parameters format
             */
            java.sql.Date tourDate = convertTourDate(tourDateSt);
            int tourDays = Integer.parseInt(tourNightsSt);
            String departureCity = convertTourDepartureCity(tourDepartureCitySt);
            Time departureTime = null;
            BigDecimal tourPrice = new BigDecimal(Integer.parseInt(tourPriceSt));
            Hotel hotel = convertTourHotel(hotelName, Integer.parseInt(hotelStars), hotelRegion);
            Food food = Food.valueOf(tourFood);

            /**
             * Create object tour, set tour data and add tour to tourList
             */
            Tour tour = new Tour(tourDate, tourDays, departureCity, departureTime, tourPrice, hotel, food);
            tour.setAdultAmount(adults);
            tour.setChildrenAmount(children);
            tour.setRoomType(convertTourRoomType(roomTypeSt));
            tourList.add(tour);

            /**
             * There are tour data, that aren't in Jsoup document(parameter of this method)
             * But part of url parameters, that is need to create connection, is in this document.
             * Creating of new connection get about 4 seconds.
             * This part of code get this part of url parameters & save them to tourIdMap.
             * Then it would be use in public method parseAdvanceData(Tour tour).
             */
            String hotelLink = tableCellList.get(TABLE_CELL_HOTEL_LINK).select(TAG_A).first().
                               attr(ATTR_ONCLICK).replaceAll(REGEXP_REPLACEMENT, "");

            String[] tourIdArray = hotelLink.split(",");
            tourIdMap.put(tour, tourIdArray);
        }
    }

    /**
     * @param date - tour date in String format dd.MM.yy
     * @return tour date in java.sql.Date format
     */
    private java.sql.Date convertTourDate(String date){
        Date utilDate;
        java.sql.Date sqlDate = null;
        try {
            utilDate = SIMPLE_DATE_FORMAT.parse(date);
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            //TODO improve
             return new java.sql.Date(0);
        }
        return sqlDate;
    }

    /**
     * @param departureCity - departure city name in Russian
     * @return departure city name in Ukrainian.
     *         If there are no Ukrainian analog in parser_properties/departure_city_ru-ua_vocabulary,
     *         return departure city name in Russian
     */
    private String convertTourDepartureCity(String departureCity){
        try {
            String departureCityUa = departureCityVocabulary.getProperty(departureCity);
            return departureCityUa;
        } catch (NullPointerException e) {
            return departureCity;
        }
    }

    private Hotel convertTourHotel(String hotelName, int hotelStars, String hotelRegion){
        Country hotelCountry = new Country(country);
        Region hotelReg = new Region(hotelRegion, hotelCountry);
        return new Hotel(hotelName, hotelStars, hotelReg);
    }

    /**
     * This method convert room type from String format to enum (com.softserveinc.softtour.entity.template.RoomType)
     * @param roomTypeSt - this parameter represents type of room in hotel.
     *        Sometimes, page with tours contains such room type as FAMI..., APAR..., etc.
     *        That's mean, room type really is FAMILY, APARTMENT, but has some advanced explanation of their type.
     *        To convert them to enum type, that this room types really are used switch in the catch block.
     * @return room type, represented by enum RoomType object.
     */
    private RoomType convertTourRoomType(String roomTypeSt){
        RoomType roomType;
        try {
            roomType = RoomType.valueOf(roomTypeSt);
        } catch (IllegalArgumentException e) {
            switch (roomTypeSt) {
                case WRONG_FAMILY_ROOM_TYPE: {
                    roomType = RoomType.FAMILY;
                    break;
                } case  WRONG_APARTMENT_ROOM_TYPE: {
                    roomType = RoomType.APART;
                    break;
                } case WRONG_DBL_ROOM_TYPE: {
                    roomType =RoomType.DBL;
                    break;
                } default: {
                    roomType = RoomType.UNKNOWN;
                    break;
                }
            }
        }
        return roomType;
    }

    public void parseAdvanceData(Tour tour){
        String[] tourIdArray = tourIdMap.get(tour);
        String url = ItTourParserUrlGenerator.createHotelInfoUrl(tourIdArray);
        Document document = connect(url);
        setHotelImage(document, tour.getHotel());
        setTourDepartureTime(document, tour);
    }

    private void setHotelImage(Document document, Hotel hotel){
        Element img = document.getElementById(ID_IMG);
        String imgUrl;
        try {
            imgUrl = img.attr(ATTR_SRC);
        } catch (NullPointerException e) {
            imgUrl = null;
        }
        hotel.setImgUrl(imgUrl);
    }

    private void setTourDepartureTime(Document document, Tour tour){
        List<Element> advanceDataList = document.getElementsByClass(CLASS_TR_FLIGHT_TO).first().getElementsByTag(TAG_TD);
        Date utilDate;
        Time timeDeparture = null;
        String departureDate = advanceDataList.get(ADVANCE_DATA_DEPARTURE_DATE).text();
        //TODO Maybe date with day of week in format like "пт", "сб" is parseable?
        departureDate = departureDate.substring(0, departureDate.length() - 3);
        String departureTime = advanceDataList.get(ADVANCE_DATA_DEPARTURE_TIME).text();
        String dateTime = departureDate + departureTime;
        try {
            utilDate = SIMPLE_DATE_TIME_FORMAT.parse(dateTime);
            timeDeparture = new Time(utilDate.getTime());
        } catch (ParseException e) {
            //TODO improve this block
            e.printStackTrace();
        }
        tour.setDepartureTime(timeDeparture);
    }

    public static void main(String[] args) {
        ItTourParser parser = new ItTourParser("Єгипет", 338, 3, 1 ,1000, 3000, 2);

        List<Tour> listTour = parser.parse();
        for(Tour tour : listTour) {
            System.out.println(tour);
        }
        for(int i = 0;i<listTour.size() ;i++) {
            parser.parseAdvanceData(listTour.get(i));
            System.out.println(listTour.get(i).getHotel().getImgUrl());
            System.out.println(listTour.get(i).getDepartureTime());
        }
        /*
        for now, full search works only for this regions:
        #Єгипет
        Дахаб = 5486
        Макаді\ Бей = 2
        Марса\ Алам = 5478

        #Туреччина
        Аланья = 6192
        Анталія = 5200
        Белек = 6818

        #Греция
        Агія\ Тріада = 9602
        Астіпалея = 43490
        Аттика = 15226

          ADVANCE SEARCH CONSTRUCTOR VALUES ORDER:
          String country, String region, int [] hotelStars, String[] food, int adults, int children, String dateFrom, String dateTo,
          int nightsFrom, int nightsTo, int priceMin, int priceMax, int pageNumber
        */
        /*
        Set<Integer> hotelStars = new HashSet<>();
        hotelStars.add(3);
        hotelStars.add(5);

        Set<String> food = new HashSet<>();
        food.add("AI");
        food.add("UAI");
        long dateStart = new Date().getTime();
        ItTourParser parser = new ItTourParser("Туреччина", "Аланья", hotelStars, food, 2, 1, "01.11.14", "31.12.14",
                                               5, 15, 500, 5000, 2);
        */
        /*
        long dateTo = new Date().getTime();
        System.out.println((dateTo - dateStart) + " milisec.");

        //set img url
        Tour tour = listTour.get(0);

        parser.parseAdvanceData(tour);
        System.out.println(tour.getHotel().getImgUrl());
        System.out.println(tour.getDepartureTime());
        */
        /*
        Hotel hotel = new Hotel("Adela Hotel", 3, new Region("Стамбул", new Country("Турция")));
        hotel.setId(59466);
        hotel.getRegion().setId(5498);
        hotel.getRegion().getCountry().setId(318);
        ItTourParser parser = new ItTourParser(hotel, 2);
        List<Tour> tours = parser.parse();
        for(Tour tour : tours){
            System.out.println(tour);
        }
        */
    }
}
