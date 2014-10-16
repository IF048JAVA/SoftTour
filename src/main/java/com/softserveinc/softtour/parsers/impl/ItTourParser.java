package com.softserveinc.softtour.parsers.impl;

import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Region;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.template.Food;
import com.softserveinc.softtour.entity.template.RoomType;
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

public class ItTourParser {
    private List<Tour> tourList = new ArrayList<>();
    private String country;
    private int adults;
    private int children;
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

    public List<Tour> parse(){
        Document document = search(url);
        // Document document = search("http://www.ittour.com.ua/tour_search.php?callback=jQuery17105286387244705111_1413460276773&module_type=tour_search&id=5062D1884G6M7121819576&ver=1&type=2970&theme=38&action=package_tour_search&hotel_rating=3+4+78&food=496+388+498+512+560+1956&hotel=&region=&child_age=&package_tour_type=1&tour_kind=0&country=338&adults=2&children=0&date_from=16.10.14&date_till=26.10.14&night_from=6&night_till=14&price_from=0&price_till=99000&switch_price=USD&departure_city=2014&items_per_page=50&module_location_url=http%3A%2F%2Ftyr.com.ua%2Ftours%2Fsearch.php&preview=1&_=1413460280572");
        addTours(document);
        return tourList;
    }

    private Document search(String url){
        String doc = null;
        try {
            doc = Jsoup.connect(url).
                  timeout(100000).
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

    private void addTours(Document document){
        /*
        90:n  n n Хургадаn Mirage New Hawaii Resort & Span 4n Familyn AIn 7n n 02.11.14n 7196 418 €533 $n n  n
listleft : 0 Хургада
listleft : 1 Mirage New Hawaii Resort & Spa
listleft : 2 Family
listleft : 3
listCenter : 0 4
listCenter : 1 AI
listCenter : 2 7
listCenter : 3 02.11.14
listCenter : 4
listRight : 0 7196
listRight : 1 7196
listRight : 2
listRight : 3 418 €
listRight : 4 418
listRight : 5 €
listRight : 6 533 $
listRight : 7 533
listRight : 8 $
        * */
        List<Element> tableRow = document.getElementsByTag("tr");
        for(int i = 3; i<tableRow.size()-2; i++){
            List<Element> listLeft = tableRow.get(i).getElementsByClass("itt_text-left");
            List<Element> listCenter = tableRow.get(i).getElementsByClass("text-center");
            List<Element> listRight = tableRow.get(i).getElementsByClass("text-right").get(0).getElementsByTag("span");
            Tour tour = new Tour();

            //set days
            int days = Integer.parseInt(listCenter.get(2).text());
            tour.setDays(days);

            //set price
            int price$ = Integer.parseInt(listRight.get(7).text());
            tour.setPrice(new BigDecimal(price$));

            //set food
            String foodSt = listCenter.get(1).text();
            Food food = Food.valueOf(foodSt);
            tour.setFood(food);

            //set departure time & date
            SimpleDateFormat format = new SimpleDateFormat("dd.mm.yy");
            String depTime = listCenter.get(3).text();
            Date departure = null;
            java.sql.Date sqlDateDepart = null;
            Time sqlTimeDepart = null;
            try {
                departure = format.parse(depTime);
                sqlDateDepart = new java.sql.Date(departure.getTime());
                sqlTimeDepart = new Time(departure.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tour.setDepartureTime(sqlTimeDepart);
            tour.setDate(sqlDateDepart);

            //set departure city
            String depCity = "";
            try{
                depCity = listLeft.get(3).getElementsByTag("div").first().text();
            }catch (NullPointerException e){
                depCity = "Без перельоту";
            }
                tour.setDepartureCity(depCity);

            //set adults & children
            tour.setAdultAmount(adults);
            tour.setChildrenAmount(children);

            //set room type
            String roomTypeSt = listLeft.get(2).text().toUpperCase();
            RoomType roomType = null;
            try {
                roomType = RoomType.valueOf(roomTypeSt);
            }catch (IllegalArgumentException e){
                if(roomTypeSt.equals("APAR...")){
                    roomType = RoomType.APART;
                } else if(roomTypeSt.equals("FAMI...")){
                    roomType = RoomType.FAMILY;
                } else {
                    roomType = RoomType.UNKNOWN;
                }
            }
            tour.setRoomType(roomType);


            //set hotel
            String hotelName = listLeft.get(1).text();
            String starsSt = listCenter.get(0).text();
            int hotelStars = Integer.parseInt(starsSt);
            Country coun = new Country(country);
            String regionName = listLeft.get(0).text();
            Region hotelRegion = new Region(regionName, coun);
            Hotel hotel = new Hotel(hotelName, hotelStars, hotelRegion);
            tour.setHotel(hotel);

            //set data from hotel page

            //set hotel img
            if(hotelHolder.containsHotel(hotelName)){
                hotel.setImgUrl(hotelHolder.getHotelPicture(hotelName));
            } else {
                Element link = listLeft.get(1).select("a").first();
                String value = link.attr("onclick").replace("return package_tour_order(", "").replace(");", "");
                String[] id = value.split(",");
                ItTourParserUtil parserUtil = new ItTourParserUtil();
                String url = parserUtil.hotelInfoUrl(id);
                Document docum = search(url);
                Element img = docum.getElementById("main_img_tour_in_view_open_");
                String imgUrl = "";
                try {
                    imgUrl = img.attr("src");
                    hotel.setImgUrl(imgUrl);
                } catch (NullPointerException e) {
                    hotel.setImgUrl("no_img");
                }
                hotelHolder.putHotel(hotelName, imgUrl);
            }

            //set

            tourList.add(tour);
        }
    }

    public static void main(String[] args) {
        ItTourParser parser = new ItTourParser("Туреччина", 3, 1 ,500, 5000, 2);
        List<Tour> listTour = parser.parse();
        for(Tour tour : listTour){
            System.out.println(tour);
        }
    }
}