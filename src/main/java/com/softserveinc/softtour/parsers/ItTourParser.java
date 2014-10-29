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
    private Map<Tour, Element> hotelElementMap;

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
        hotelElementMap = new HashMap<>();
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
        hotelElementMap = new HashMap<>();
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
        hotelElementMap = new HashMap<>();
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
         * Page comes unparseable. Removing one backslash solve this problem.
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
            System.err.println("Can't find file: " + DEPARTURE_CITY_PROPERTIES_PATH);
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

            String tourDateSt = tableCellList.get(7).text();
            String tourNightsSt = tableCellList.get(6).text();
            String tourDepartureCitySt = tableCellList.get(3).getElementsByTag(TAG_DIV).first().text();
            String tourDepartureTimeSt = tableCellList.get(7).text();
            String tourPriceSt = tableCellList.get(9).getElementsByTag(TAG_SPAN).get(7).text();
            String hotelName = tableCellList.get(1).text();
            String hotelStars = tableCellList.get(4).text();
            String hotelRegion = tableCellList.get(0).text();
            Element hotelLink = tableCellList.get(1).select(TAG_A).first();
            String tourFood = tableCellList.get(5).text();
            String roomTypeSt = tableCellList.get(2).text().toUpperCase();

            java.sql.Date tourDate = tourDate(tourDateSt);
            int tourDays = Integer.parseInt(tourNightsSt);
            String departureCity = tourDepartureCity(tourDepartureCitySt);
            Time departureTime = tourDepartureTime(tourDepartureTimeSt);
            BigDecimal tourPrice = new BigDecimal(Integer.parseInt(tourPriceSt));
            Hotel hotel = tourHotel(hotelName, Integer.parseInt(hotelStars), hotelRegion);
            Food food = Food.valueOf(tourFood);

            Tour tour = new Tour(tourDate, tourDays, departureCity, departureTime, tourPrice, hotel, food);
            tour.setAdultAmount(adults);
            tour.setChildrenAmount(children);
            tour.setRoomType(tourRoomType(roomTypeSt));

            hotelElementMap.put(tour, hotelLink);

            tourList.add(tour);
        }
    }

    private java.sql.Date tourDate(String date){
        Date javaUtilDate;
        java.sql.Date sqlDate = null;
        try {
            javaUtilDate = SIMPLE_DATE_FORMAT.parse(date);
            sqlDate = new java.sql.Date(javaUtilDate.getTime());
        } catch (ParseException e) {
             //TODO improve this block
            e.printStackTrace();
        }
        return sqlDate;
    }

    private String tourDepartureCity(String departureCity){
        try {
            String departureCityUa = departureCityVocabulary.getProperty(departureCity);
            return departureCityUa;
        } catch (NullPointerException e) {
            return WITHOUT_FLY;
        }
    }

    private Time tourDepartureTime(String departureTime){
        Date javaUtilDate;
        Time timeDeparture = null;
        try {
            javaUtilDate = SIMPLE_DATE_FORMAT.parse(departureTime);
            timeDeparture = new Time(javaUtilDate.getTime());
        } catch (ParseException e) {
            //TODO improve this block
            e.printStackTrace();
        }
        return timeDeparture;
    }

    private Hotel tourHotel(String hotelName, int hotelStars, String hotelRegion){
        Country hotelCountry = new Country(country);
        Region hotelReg = new Region(hotelRegion, hotelCountry);
        Hotel hotel = new Hotel(hotelName, hotelStars, hotelReg);
        return hotel;
    }

    //TODO improve!!!
    public void setHotelImgLinkAndDepartureTime(Tour tour){
        Element hotelLink = hotelElementMap.get(tour);
        String tourId = hotelLink.attr(ATTR_ONCLICK).replaceAll(REGEXP_REPLACEMENT, "");
        String[] tourIdArr = tourId.split(",");
        String url = ItTourParserUrlGenerator.createHotelInfoUrl(tourIdArr);
        Document document = connect(url);
        Element img = document.getElementById(ID_IMG);
        String imgUrl;
        try {
            imgUrl = img.attr(ATTR_SRC);
        } catch (NullPointerException e) {
            imgUrl = null;
        }
        tour.getHotel().setImgUrl(imgUrl);

        Date javaUtilDate;
        Time timeDeparture = null;
        List<Element> elementList = document.getElementsByClass(CLASS_TR_FLIGHT_TO).get(0).getElementsByTag(TAG_TD);
        String departureDate = elementList.get(3).text();
        departureDate = departureDate.substring(0, departureDate.length() - 3);
        String departureTime = elementList.get(4).text();
        String dateTime = new StringBuilder(departureDate).append(departureTime).toString();
        System.out.println(dateTime);
        try {
            javaUtilDate = SIMPLE_DATE_TIME_FORMAT.parse(dateTime);
            timeDeparture = new Time(javaUtilDate.getTime());
        } catch (ParseException e) {
            //TODO improve this block
            e.printStackTrace();
        }
        tour.setDepartureTime(timeDeparture);
    }

    private RoomType tourRoomType(String roomTypeSt){
        RoomType roomType;
        try {
            roomType = RoomType.valueOf(roomTypeSt);
        } catch (IllegalArgumentException e) {
            switch (roomTypeSt) {
                case WRONG_APARTMENT_ROOM_TYPE: {
                    roomType = RoomType.APART;
                    break;
                }
                case WRONG_FAMILY_ROOM_TYPE: {
                    roomType = RoomType.FAMILY;
                    break;
                }
                default: {
                    roomType = RoomType.UNKNOWN;
                    break;
                }
            }
        }
        return roomType;
    }

    public static void main(String[] args) {
        ItTourParser parser = new ItTourParser("Єгипет", 338, 3, 1 ,1000, 3000, 2);
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
        List<Tour> listTour = parser.parse();
        for(Tour tour : listTour) {
            System.out.println(tour);
        }
        for(int i = 0;i<4;i++) {
            parser.setHotelImgLinkAndDepartureTime(listTour.get(i));
            System.out.println(listTour.get(i).getHotel().getImgUrl());
            System.out.println(listTour.get(i).getDepartureTime());
        }
        /*
        long dateTo = new Date().getTime();
        System.out.println((dateTo - dateStart) + " milisec.");

        //set img url
        Tour tour = listTour.get(0);

        parser.setHotelImgLinkAndDepartureTime(tour);
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
