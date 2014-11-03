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
import java.sql.Time;
import java.text.ParseException;
import java.util.*;

/**
 * This class finds tours and supported information by input parameters.
 * The tours data source is site: http://www.ittour.com.ua
 * This class use:
 *     com.softserveinc.softtour.parsers.constants.ItTourParserConstants - contains used in this class constants
 *     com.softserveinc.softtour.entity.Tour - represent tour
 *     com.softserveinc.softtour.util.ItTourParserUrlGenerator - generate web-page's url, that contains tours.
 * Class contains three overloaded constructors for such pages of our web-application:
 *     index page with quick search
 *     search page with advanced search
 *     hotels page with search by hotel
 */
public class ItTourParser implements ItTourParserConstants {

    /**
     * Parse results will be save in this list
     */
    private List<Tour> tourList;
    private Country country;
    private int adults;
    private int children;

    /**
     * Web-page's url, that will be use in Jsoup connection to get object Document(encapsulate web page with tours)
     */
    private String url;

    /**
     * This properties will be load in method parse(). Contains ru-ua vocabulary of the departure cities.
     * Reason: www.ittour.com.ua contains names of cities in Russian, when our web-application in Ukrainian.
     */
    private Properties departureCityVocabulary;

    /**
     * This constructor must be use for quick search
     * @param country - tour country
     * @param countryParam - tour country parameter(country code, one of the url parameter)
     * @param adults - count adults, that plan to go to the tour
     * @param children - count children, that plan to go to the tour
     * @param priceMin - minimum money value, that can be pay for the tour
     * @param priceMax - maximum money value, that can be pay for the tour
     * @param pageNumber - number of page, that will be parse.
     *         Parse connect to the page, that can contains 20, 50 or 100 tours (This value contains in constant:
     *         ITEMS_PER_PAGE_VALUE in com.softserveinc.softtour.util.constants.ItTourParserUrlGeneratorConstants).
     *
     *         Example: If pageNumber will be equals 1, this parser will be connect to the first page with result,
     *         required to input parameters. Number 2 - to second and so on.
     */
    public ItTourParser(String country, long countryParam, int adults, int children, int priceMin, int priceMax,
                int pageNumber) {
        tourList = new ArrayList<>();
        this.country = new Country(country);
        this.adults = adults;
        this.children = children;
        url = ItTourParserUrlGenerator.createQuickSearchUrl(countryParam, adults, children, priceMin, priceMax,
                pageNumber);
        departureCityVocabulary = new Properties();
    }

    /**
     * This constructor must be use for advanced search
     * @param country - tour country
     * @param countryParam - tour country parameter(country code, one of the url parameter)
     * @param regionParam - tour region parameter(region code, one of the url parameter)
     * @param hotelStars - count of hotel stars. Set, that must contains values from 2 to 5.
     * @param food - type of food. Set with types of food. Must contains values, equals to the enum Food objects
     * @param adults - count adults, that plan to go to the tour
     * @param children - count children, that plan to go to the tour
     * @param dateFrom - from this date tour will be search. Value must be in format: dd.MM.yy
     * @param dateTo - tour will be search to this date. Value must be in format: dd.MM.yy
     * @param nightsFrom - minimum count of tour nights. Value must be from 1 to 21.
     * @param nightsTo - maximum count of tour nights. Value must be from 1 to 21.
     * @param priceMin - minimum money value, that can be pay for the tour
     * @param priceMax - maximum money value, that can be pay for the tour
     * @param pageNumber - number of page, that will be parse.
     *         Parse connect to the page, that can contains 20, 50 or 100 tours (This value contains in constant:
     *         ITEMS_PER_PAGE_VALUE in com.softserveinc.softtour.util.constants.ItTourParserUrlGeneratorConstants).
     *
     *         Example: If pageNumber will be equals 1, this parser will connect to first page with result,
     *         required to input parameters. Number 2 - to second and so on.
     */
    public ItTourParser(String country, long countryParam, long regionParam, Set<Integer> hotelStars, Set<String> food,
                int adults, int children, String dateFrom, String dateTo, int nightsFrom, int nightsTo, int priceMin,
                int priceMax, int pageNumber) {
        tourList = new ArrayList<>();
        this.country = new Country(country);
        this.adults = adults;
        this.children = children;
        url = ItTourParserUrlGenerator.createAdvanceSearchUrl(countryParam, regionParam, hotelStars, food, adults,
                children, dateFrom, dateTo, nightsFrom, nightsTo, priceMin, priceMax, pageNumber);
        departureCityVocabulary = new Properties();
    }

    /**
     * This constructor must be use for search by hotel.
     * @param hotel - object hotel
     * @param pageNumber - number of the page, that will be parse.
     *         Parse connect to the page, that can contains 20, 50 or 100 tours (This value contains in constant:
     *         ITEMS_PER_PAGE_VALUE in com.softserveinc.softtour.util.constants.ItTourParserUrlGeneratorConstants).
     *
     *         Example: If pageNumber will be equals 1, this parser will connect to first page with result,
     *         required to input parameters. Number 2 - to second and so on.
     */
    public ItTourParser(Hotel hotel, int pageNumber) {
        tourList = new ArrayList<>();
        this.country = hotel.getRegion().getCountry();
        this.adults = DEFAULT_ADULTS_COUNT;
        this.children = DEFAULT_CHILDREN_COUNT;
        url = ItTourParserUrlGenerator.createSearchUrlByHotel(hotel, pageNumber);
        departureCityVocabulary = new Properties();
    }

    /**
     * This method creates connection to the web-page with tours that required constructor parameters.
     *
     * Web-page with tours is encapsulated in Jsoup object Document.
     * Document will be generate by using private method connect.
     *
     * Private method loadDepartureCityProperties load properties file data.
     * This data contains ru-ua vocabulary of the departure cities.
     * Our application contains Ukrainian names of cities, when parseable site - Russian.
     * So there is the need to translate cities names.
     *
     * Methods addTours add tours to tourList.
     *
     * @return list of the tours (represented by Tour class)
     */
    public List<Tour> parse() {
        Document document = connect(url);
        loadDepartureCityProperties();
        addTours(document);
        return tourList;
    }

    /**
     * @param url - web-page's url
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
            e.printStackTrace();
        }

        /**
         * Page is unparseable. Removal one backslash solve this problem.
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
     * This method gets from the document tour data, create Tour objects and add them to the tourList
     * @param document - web-page, encapsulated in Jsoup object Document
     */
    private void addTours(Document document) {

        /**
         * This list contains rows from table in the web-page with tours.
         * The data, that will be use, starts from the third row. Last two rows haven't useful data.
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
             *    9   : Nine tags span with tour price. The second is in hrn, the fifth in €, the eighth in $,
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
            String roomTypeSt = tableCellList.get(TABLE_CELL_TOUR_ROOM_TYPE).text().toUpperCase().replaceAll(" ","_");

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
             * Create object tour, set tour data
             */
            Tour tour = new Tour(tourDate, tourDays, departureCity, departureTime, tourPrice, hotel, food);
            tour.setAdultAmount(adults);
            tour.setChildrenAmount(children);
            tour.setRoomType(convertTourRoomType(roomTypeSt));

            /**
             * There are tour data, that aren't in Jsoup document(parameter of this method)
             * But part of the url parameters, that is need to create connection, is in this document.
             * Creation of the new connection gets about 4 seconds.
             * This part of code gets the part of the url parameters and set them to the tour.
             * Then it will be use in public method parseAdvanceData(Tour tour).
             */
            String hotelLink = tableCellList.get(TABLE_CELL_HOTEL_LINK).select(TAG_A).first().
                    attr(ATTR_ONCLICK).replaceAll(REGEXP_REPLACEMENT, "");

            String[] tourIdArray = hotelLink.split(",");
            tour.setItTourId(tourIdArray);

            tourList.add(tour);
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
     * @return Departure city name in Ukrainian.
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

    /**
     * @param hotelName - hotel name in String format
     * @param hotelStars - count of hotel stars (2-5)
     * @param hotelRegion - hotel region in String format
     * @return object Hotel
     */
    private Hotel convertTourHotel(String hotelName, int hotelStars, String hotelRegion){
        Region hotelReg = new Region(hotelRegion, country);
        return new Hotel(hotelName, hotelStars, hotelReg);
    }

    /**
     * This method convert room type from String format to enum (com.softserveinc.softtour.entity.template.RoomType)
     * @param roomTypeSt - this parameter represents type of room in hotel.
     *        Sometimes, page with the tours contains such room type as FAMI..., APAR..., etc.
     *        That's mean, room type really is FAMILY, APARTMENT, but has some advanced explanation of their type.
     *        To convert them to the enum type, that this room types really are used switch in the catch block.
     * @return room type, represented by enum RoomType object.
     */
    private RoomType convertTourRoomType(String roomTypeSt){
        RoomType roomType;
        try {
            roomType = RoomType.valueOf(roomTypeSt);
        } catch (IllegalArgumentException e) {
            switch (roomTypeSt) {
                case WRONG_FAMILY_ROOM_TYPE:{
                    roomType = RoomType.FAMILY;
                    break;
                } case  WRONG_APARTMENT_ROOM_TYPE:{
                    roomType = RoomType.APART;
                    break;
                } case WRONG_DBL_ROOM_TYPE:{
                    roomType = RoomType.DBL;
                    break;
                } case WRONG_CLASSIC_ROOM_TYPE:{
                    roomType = RoomType.CLASSIC_ROOM;
                    break;
                } case WRONG_DOUBLE_ROOM_TYPE:{
                    roomType = RoomType.DOUBLE;
                    break;
                } default: {
                    roomType = RoomType.UNKNOWN;
                    break;
                }
            }
        }
        return roomType;
    }

    /**
     * This method parse advance data: hotel image and tour departure time.
     * There are tour data, that aren't in Jsoup document with tours.
     * But part of the url parameters, that is need to create connection, is in this document.
     * Creating of the new connection get about 4 seconds. It is unproductively.
     * So, there is need to create additional method.
     * This method used when user open tour to check for additional tour information.
     * @param tour - information about tour, represented by Tour object
     */
    public void parseAdvanceData(Tour tour){
        String[] tourIdArray = tour.getItTourId();
        String url = ItTourParserUrlGenerator.createAdvanceDataUrl(tourIdArray);
        Document document = connect(url);
        setHotelImage(document, tour.getHotel());
        setTourDepartureTime(document, tour);
    }

    /**
     * This method find the hotel image url and set it to the hotel. Used in public method parseAdvanceData(Tour tour).
     * @param document - encapsulated in Jsoup object Document web-page with hotel image
     * @param hotel - information about hotel, represented by Hotel class
     */
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

    /**
     * This method finds tour departure time and set it to the tour. Used in public method parseAdvanceData(Tour tour).
     * @param document - encapsulated in Jsoup object Document web-page with tour departure time.
     * @param tour - information about tour, represented by Tour class
     */
    private void setTourDepartureTime(Document document, Tour tour){
        List<Element> dateList = document.getElementsByClass(CLASS_TR_FLIGHT_TO).first().getElementsByTag(TAG_TD);
        String departureDate = dateList.get(ADVANCE_DATA_DEPARTURE_DATE).text();
               departureDate = departureDate.substring(0, departureDate.length() - TAL_DATE_DATA);
        String departureTime = dateList.get(ADVANCE_DATA_DEPARTURE_TIME).text();
        Date utilDate;
        Time sqlTime;
        String dateTime = departureDate + departureTime;
        try {
            utilDate = SIMPLE_DATE_TIME_FORMAT.parse(dateTime);
            sqlTime = new Time(utilDate.getTime());
        } catch (ParseException e) {
            //TODO improve this block
            sqlTime = new java.sql.Time(0);
        }
        tour.setDepartureTime(sqlTime);
    }

    @Override
    public String toString() {
        return "Tour parser. Source: http://www.ittour.com.ua " + "Current url: " + url;
    }
}
