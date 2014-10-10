package com.softserveinc.softtour.parsers.impl;

import com.softserveinc.softtour.entity.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class ItTourParser {
    private String baseParams = "http://www.ittour.com.ua/tour_search.php?" +
            "callback=jQuery17109648473216220737_1412803322658&id=5062D1884G6M7121819576&ver=1&type=2970&theme=38" +
            "&action=package_tour_search&package_tour_type=0&tour_kind=0&default_form_select=1&items_per_page=100&preview=1";
    private String country;
    private int adults;
    private int children;
    private int priceFrom;
    private int priceTo;
    private List<Tour> tourList = new ArrayList<>();
    private Properties countryProperties = new Properties();

    {
        try {
            InputStream inputCountry = this.getClass().
                    getResourceAsStream("/parser_properties/it_tour_country_parameters");
            countryProperties.load(new InputStreamReader(inputCountry, "UTF-8"));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public ItTourParser(String country, int adults, int children, int priceFrom, int priceTo) {
        this.country = country;
        this.adults = adults;
        this.children = children;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
    }

    public List<Tour> parse(){
        String url = creareURL();
        searchTours(url);
        return tourList;
    }

    private String creareURL(){
        StringBuilder stringBuilder = new StringBuilder(baseParams);
        stringBuilder.append("&country=").append(countryProperties.getProperty(country));
        stringBuilder.append("&adults=").append(adults);
        stringBuilder.append("&children=").append(children);
        stringBuilder.append("&price_from=").append(priceFrom);
        stringBuilder.append("&price_till=").append(priceTo);
        return stringBuilder.toString();
    }

    private void searchTours(String url){
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
        doc = doc.replace("\\","");
        Document document = Jsoup.parse(doc);
        addTours(document);
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
            Food food = new Food(foodSt);
            tour.setFood(food);

            //set departure time & date
            SimpleDateFormat format = new SimpleDateFormat("dd.mm.yy");
            String depTime = listCenter.get(3).text();
            Date departure = null;
            java.sql.Date sqlDateDepart = null;
            try {
                departure = format.parse(depTime);
                sqlDateDepart = new java.sql.Date(departure.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tour.setDepartureTime(sqlDateDepart);
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
        ItTourParser parser = new ItTourParser("Греція", 3, 1 ,500, 1000);
        List<Tour> list = parser.parse();
        for(Tour tour : list){
            System.out.println(tour);
        }
    }
}