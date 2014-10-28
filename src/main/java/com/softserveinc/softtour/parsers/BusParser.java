package com.softserveinc.softtour.parsers;

import com.softserveinc.softtour.bean.BusRoute;
import com.softserveinc.softtour.parsers.constants.ParsersConstants;
import com.softserveinc.softtour.util.BusParserUrlGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* This class find routes from user's chosen city to tour departure city.
* The routes data source is site: http://ticket.bus.com.ua
* This class use:
*   com.softserveinc.softtour.parsers.constants.ParsersConstants - contains used in this class constants
*   com.softserveinc.softtour.bean.BusRoute - represent bus route
*   com.softserveinc.softtour.util.BusParserUrlGenerator - generate web-page's url, that contains bus routes.
*/
public class BusParser implements ParsersConstants {
    /**
     * Parse results would be save in this list
     */
    private List<BusRoute> busRouteList;
    private String cityFrom;
    private String cityTo;
    /**
    * This variable represents date & time, when user go to tour from departure city
    */
    private Date departureDateTime;

    /**
    * Creates new BusParser with params:
    * @param cityFrom - user's chosen city
    * @param cityTo - tour departure city
    * @param departureDate - represent date, when user go to tour from departure city, String in format yyyy-MM-dd
    * @param departureTime - represent time, when user go to tour from departure city, String in format HH:mm
    * */
    public BusParser(String cityFrom, String cityTo, String departureDate, String departureTime) {
        this.busRouteList = new ArrayList<>();
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        try {
            /**
             * This variable contains departure date & time, converted from String to java.util.Date
             */
            this.departureDateTime = SIMPLE_DATE_TIME_FORMAT.parse(departureDate + departureTime);
        }catch (ParseException e){
            System.err.print("Unparsable data. Departure date - " + departureDate + " must be in format yyyy-MM-dd " +
                    "Departure time " + departureTime + " must be in format - HH:mm ");
        }
    }

    /**
     * This method create two connections:
     *     First connection find routes on day, that is three hours earlier departureDateTime.
     *         The reason is that client must come to departure point earlier to pass registration.
     *         The registration passes about three hours.
     *     Second connection find routes on day, that is the day earlier then first connection.
     *
     *     Example: Departure date: 2014-11-12, departure time: 02:00.
     *     First connection will find routes on date: 2014-11-11.
     *     Second connection will find routes on date: 2014-11-10.
     *
     *     In this case Strings firstDateUrl and secondDateUrl are the urls with reduced date parameters.
     *     We receive this Strings by using private method generateUrl.
     *
     * @return list of bus routes (represented by BusRoute class)
     */
    public List<BusRoute> parse() {
        String firstDateUrl = generateUrl(FIRST_DATE_REDUCE);
        String secondDateUrl = generateUrl(SECOND_DATE_REDUCE);
        Document document = connect(firstDateUrl);
        addBuses(document);
        document = connect(secondDateUrl);
        addBuses(document);
        removeWasteRouts();
        return busRouteList;
    }

    private String generateUrl(int dateReduce){
        Date reducedDate = new Date(departureDateTime.getTime() - dateReduce);
        String resultDateString = SIMPLE_DATE_FORMAT.format(reducedDate);
        BusParserUrlGenerator generator = new BusParserUrlGenerator();
        String url = generator.createSearchUrl(cityFrom, cityTo, resultDateString);
        return url;
    }

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
        Document document = Jsoup.parse(doc);
        return document;
    }

    private void addBuses(Document document){
        /*
        [index]:[value]
           0   : Tags tg, div & 4 span, not used
           1   : Bus date departure
           2   : Bus station contacts & tag b with departure time
           3   : Arrival time & city from in tag small
           4   : Price
           5   : ???
           6   : Percent of route stability
           7   : Route code & name
           8   : Mark of bus
           9   : Img
           10  : Empty tag td
        */
        List<Element> elementListSmall = document.getElementsByTag(TAG_TR);
        for(int i = 7; i < elementListSmall.size() - 5; i++){
            List<Element> dataList = elementListSmall.get(i).getElementsByTag(TAG_TD);
            if (dataList.size() < 7){
                return;
            }
            String id = dataList.get(7).text();
            String departureCity = cityFrom;
            String arrivalCity = cityTo;
            String departureDate = dataList.get(1).text();
            String departureTime = dataList.get(2).getElementsByTag(TAG_B).first().text();
            String arrivalTime = dataList.get(3).ownText();
            String price = dataList.get(4).text();
            String onWayTime = calculateOnWayTime(departureTime, arrivalTime);

            BusRoute route = new BusRoute(id, departureCity, arrivalCity, departureDate, departureTime, onWayTime,
                             arrivalTime, price, price);
            busRouteList.add(route);
         }
    }

    private String calculateOnWayTime(String departureTime, String arrivalTime){
        int depHours = Integer.parseInt(departureTime.substring(0, 2));
        int depMinutes = Integer.parseInt(departureTime.substring(3, 5));
        int arrHours = Integer.parseInt(arrivalTime.substring(0, 2));
        int arrMinutes = Integer.parseInt(arrivalTime.substring(3, 5));
        int depAllMin = depHours*60 + depMinutes;
        int arrAllMin = arrHours*60 + arrMinutes;
        int result = arrAllMin - depAllMin;
        if(result < 0){
            result += 1440;
        }
        int resHours = result/60;
        int resMinutes = result%60;
        StringBuilder onWayTime = new StringBuilder();
        if(resHours < 10){
            onWayTime.append(0).append(resHours);
        } else {
            onWayTime.append(resHours);
        }
        onWayTime.append(":");
        if(resMinutes < 10){
            onWayTime.append(0).append(resMinutes);
        } else {
            onWayTime.append(resMinutes);
        }
        return onWayTime.toString();
    }

    private void removeWasteRouts(){
        Date marginalDate = new Date(departureDateTime.getTime() - FIRST_DATE_REDUCE);
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
        for(int i = 0; i < busRouteList.size(); i++){
            String dateAndTime = new StringBuilder(busRouteList.get(i).getDepartureDate()).append(".").
                                 append(busRouteList.get(i).getDepartureTime()).toString();
            Date routeDate = null;
            try {
                routeDate = dateFormat.parse(dateAndTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int compare = marginalDate.compareTo(routeDate);
            if(compare < 0){
                busRouteList.remove(busRouteList.get(i));
                i--;
            }
        }
    }

    //TODO DEL !!!
    public static void main(String[] args) {
        BusParser busParser = new BusParser("Львів", "Київ", "2014-11-01", "11:30");
        List<BusRoute> list = busParser.parse();
        for(BusRoute route : list){
            System.out.println(route);
        }
    }
}

