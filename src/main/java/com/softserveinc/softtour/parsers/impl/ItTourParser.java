package com.softserveinc.softtour.parsers.impl;

import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Region;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.template.Food;
import com.softserveinc.softtour.entity.template.RoomType;
import com.softserveinc.softtour.parsers.constants.ItTourParserConstants;
import com.softserveinc.softtour.util.HotelHolder;
import com.softserveinc.softtour.util.ItTourParserUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItTourParser implements ItTourParserConstants {
    private List<Tour> tourList = new ArrayList<>();
    private String country;
    private String region;
    private int[] hotelStars;
    private String[] food;
    private int adults;
    private int children;
    private String dataFrom;
    private String dataTill;
    private int nightsFrom;
    private int nightsTill;
    private ItTourParserUtil parserUtil;
    private String url;
    private HotelHolder hotelHolder;

    public ItTourParser(String country, int adults, int children, int priceFrom, int priceTo, int pageNumber) {
        this.country = country;
        this.adults = adults;
        this.children = children;
        parserUtil = new ItTourParserUtil();
        this.url = parserUtil.createQuickSearchUrl(country, adults, children, priceFrom, priceTo, pageNumber);
        hotelHolder = HotelHolder.getInstance();
    }

    public ItTourParser(String country, String region, int [] hotelStars, String[] food, int adults, int children,
                        String dataFrom, String dataTill, int nightsFrom, int nightsTill, int priceFrom, int priceTo,
                        int pageNumber) {
        this.country = country;
        this.region = region;
        this.hotelStars = hotelStars;
        this.food = food;
        this.adults = adults;
        this.children = children;
        this.dataFrom = dataFrom;
        this.dataTill = dataTill;
        this.nightsFrom = nightsFrom;
        this.nightsTill = nightsTill;
        parserUtil = new ItTourParserUtil();
        this.url = parserUtil.createAdvanceSearchUrl(country, region, hotelStars, food, adults, children, dataFrom, dataTill,
                nightsFrom, nightsTill, priceFrom, priceTo, pageNumber);
        System.out.println(url);
        hotelHolder = HotelHolder.getInstance();
    }

    public List<Tour> parse(){
        Document document = connect(url);
        addTours(document);
        return tourList;
    }

    private static Document connect(String url){
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
        String tourPage = doc.replace("\\", "");
        Document document = Jsoup.parse(tourPage);
        return document;
    }

    private void addTours(Document document) {
        List<Element> tableRow = document.getElementsByTag(TAG_TR);
        for (int i = 3; i < tableRow.size() - 2; i++) {
            /*
            listLeft
            [index]:[value]
               0   : Region
               1   : Hotel, contains link to hotel page
               2   : Room type
               3   : Tag div with departure city
             */
            List<Element> listLeft = tableRow.get(i).getElementsByClass(CLASS_ITT_TEXT_LEFT);

            /*
            listCenter
            [index]:[value]
               0   : Hotel stars
               1   : Food type
               2   : Nights count
               3   : Date Fly
               4   : ???
            */
            List<Element> listCenter = tableRow.get(i).getElementsByClass(CLASS_TEXT_CENTER);

            /*
            listRight
            [index]:[value]
               0   : Tour price hrn
               1   : Tour price hrn
               2   : ???
               3   : Tour price € (sum & mark €)
               4   : Tour price € (only sum)
               5   : Mark €
               6   : Tour price $ (sum & mark $)
               7   : Tour price $ (only sum)
               8   : Mark $
            */
            List<Element> listRight = tableRow.get(i).getElementsByClass(CLASS_TEXT_RIGHT).get(0).
                    getElementsByTag(TAG_SPAN);

            String tourDateSt = listCenter.get(3).text();
            String tourDaysSt = listCenter.get(2).text();
            String tourDepartureCitySt = listLeft.get(3).getElementsByTag(TAG_DIV).first().text();
            String tourDepartureTimeSt = listCenter.get(3).text();
            String tourPriceSt = listRight.get(7).text();
            String hotelName = listLeft.get(1).text();
            String hotelStars = listCenter.get(0).text();
            String hotelRegion = listLeft.get(0).text();
            Element hotelLink = listLeft.get(1).select(TAG_A).first();
            String tourFood = listCenter.get(1).text();
            String roomTypeSt = listLeft.get(2).text().toUpperCase();


            java.sql.Date tourDate = tourDate(tourDateSt);
            int tourDays = Integer.parseInt(tourDaysSt);
            String departureCity = tourDepartureCity(tourDepartureCitySt);
            //TODO there are no departure time on site
            Time departureTime = tourDepartureTime(tourDepartureTimeSt);
            BigDecimal tourPrice = new BigDecimal(Integer.parseInt(tourPriceSt));
            Hotel hotel = tourHotel(hotelName, Integer.parseInt(hotelStars), hotelRegion);
                  //hotel.setImgUrl(hotelImgLink(hotel, hotelLink));
            Food food = Food.valueOf(tourFood);

            Tour tour = new Tour(tourDate, tourDays, departureCity, departureTime, tourPrice, hotel, food);
            tour.setAdultAmount(adults);
            tour.setChildrenAmount(children);
            tour.setRoomType(tourRoomType(roomTypeSt));

            tourList.add(tour);
        }
    }

    private java.sql.Date tourDate(String date){
        SimpleDateFormat format = new SimpleDateFormat(DAY_FORMAT);
        Date javaUtilDate;
        java.sql.Date sqlDate = null;
        try {
            javaUtilDate = format.parse(date);
            sqlDate = new java.sql.Date(javaUtilDate.getTime());
        } catch (ParseException e) {
             //TODO improve this block
            e.printStackTrace();
        }
        return sqlDate;
    }

    private String tourDepartureCity(String departureCity){
        try {
            return departureCity;
        } catch (NullPointerException e) {
            return WITHOUT_FLY;
        }
    }

    private Time tourDepartureTime(String departureTime){
        SimpleDateFormat format = new SimpleDateFormat(DAY_FORMAT);
        Date javaUtilDate;
        Time timeDeparture = null;
        try {
            javaUtilDate = format.parse(departureTime);
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

    private String hotelImgLink(Hotel hotel, Element hotelLink){
        if (hotelHolder.containsHotel(hotel.getName())) {
            return hotelHolder.getHotelPicture(hotel.getName());
        } else {
            String tourId = hotelLink.attr(ATTR_ONCLICK).replaceAll(REGEXP_REPLACEMENT, "");
            String[] tourIdArr = tourId.split(",");
            String url = parserUtil.createHotelInfoUrl(tourIdArr);
            Document document = connect(url);
            Element img = document.getElementById(ID_IMG);
            String imgUrl;
            try {
                imgUrl = img.attr(ATTR_SRC);
            } catch (NullPointerException e) {
                imgUrl = NO_IMG;
            }
            hotelHolder.putHotel(hotel.getName(), imgUrl);
            return imgUrl;
        }
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
        //ItTourParser parser = new ItTourParser("Туреччина", 3, 1 ,500, 5000, 2);
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
          String country, String region, int [] hotelStars, String[] food, int adults, int children, String dataFrom, String dataTill,
          int nightsFrom, int nightsTill, int priceFrom, int priceTo, int pageNumber
        */
        int[] hotelStars = {3, 5};
        String[] food = {"AI", "UAI"};
        ItTourParser parser = new ItTourParser("Туреччина", "Аланья", hotelStars, food, 2, 1, "01.11.14", "31.12.14",
                                               5, 15, 500, 5000, 2);
        List<Tour> listTour = parser.parse();
        for(Tour tour : listTour){
            System.out.println(tour);
        }
    }
}
