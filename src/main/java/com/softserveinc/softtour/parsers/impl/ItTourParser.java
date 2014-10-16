package com.softserveinc.softtour.parsers.impl;

import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Region;
import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.entity.template.Food;
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
    private Document document;


    public ItTourParser(String country) {
        this.country = country;
    }

    public List<Tour> parse(String url){
        String tourPage = searchTours(url).replace("\\","");
        document = Jsoup.parse(tourPage);
        addTours(document);
        return tourList;
    }

    private String searchTours(String url){
        String doc = null;
        try {
            doc = Jsoup.connect(url).
                  timeout(5000).
                  ignoreContentType(true).
                  execute().
                  body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
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

            //set hotel
            String hotelName = listLeft.get(1).text();
            String starsSt = listCenter.get(0).text();
            int hotelStars = Integer.parseInt(starsSt);
            Country coun = new Country(country);
            String regionName = listLeft.get(0).text();
            Region hotelRegion = new Region(regionName, coun);
            Hotel hotel = new Hotel(hotelName, hotelStars, hotelRegion);
            tour.setHotel(hotel);

            tourList.add(tour);
        }
    }

    public static void main(String[] args) {
        ItTourParserUtil parserUtil = new ItTourParserUtil();
        String url = parserUtil.createUrl("Греція", 3, 1 ,500, 1000, 2);
        ItTourParser parser = new ItTourParser("Греція");
        List<Tour> listTour = parser.parse(url);
        for(Tour tour : listTour){
            System.out.println(tour);
        }
    }
}