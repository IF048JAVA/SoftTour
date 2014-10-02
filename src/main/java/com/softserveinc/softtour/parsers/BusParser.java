package com.softserveinc.softtour.parsers;

import com.softserveinc.softtour.dto.BusTransit;
import com.softserveinc.softtour.parsers.constants.BusParserConstants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.*;

public class BusParser implements BusParserConstants {
    private static Properties busParserParams = new Properties();
    private List<BusTransit> busList = new ArrayList<>();
    private String cityFrom;
    private String cityTo;
    private java.sql.Date tourDate;

    static {
        try {
            InputStream inputParams = BusParser.class.getClass().getResourceAsStream(RESOURCE_PATH_PARAMS);
            busParserParams.load(new InputStreamReader(inputParams, DEFAULT_CHARSET));
        }catch (IOException e){
            System.out.println(e.getStackTrace());
        }
    }

    public BusParser(String cityFrom, String cityTo, java.sql.Date tourDate) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.tourDate = tourDate;
    }

    public List<BusTransit> parse() {
        String url = createURL(cityFrom, cityTo);
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        }catch (IOException e){
            //TODO If there are no connect
            System.out.println(e.getMessage());
        }
        Elements buses = doc.select(BUS_SELECT);
        removeRedundantLines(buses);
        addBusesToList(buses, cityFrom, cityTo, tourDate);
        return busList;
    }

    private String createURL(String cityFrom, String cityTo) {
        StringBuffer url = new StringBuffer(URL_BUS_COM_UA);
        url.append(QUESTION_MARK).
            append(PARAM_CITY_FROM).append(EQUAL_MARK).append(busParserParams.getProperty(cityFrom)).
            append(AMPERSAND_MARK).
            append(PARAM_CITY_TO).append(EQUAL_MARK).append(busParserParams.getProperty(cityTo)).
            append(AMPERSAND_MARK).
            append(PARAM_GO).append(EQUAL_MARK).append(VALUE_GO);
        return url.toString();
    }

    private void removeRedundantLines(Elements raises) {
        Iterator<Element> iterator = raises.iterator();
        while (iterator.hasNext()){
            String regularity = iterator.next().text();
            if(regularity.contains(REDUNDANT_LINE)){
                iterator.remove();
            }
        }
    }

    private void addBusesToList(Elements buses, String cityFrom, String cityTo, Date tourDate){
        for(Element element : buses) {
            BusTransit busTransit = new BusTransit();
            Elements elementsByTag = element.getElementsByTag(FIND_BY_TAG);
            String cityFromAndNameOfStation = elementsByTag.get(2).text();
            String cityToAndNameOfStation = elementsByTag.get(5).text();
            String timeDeparture = elementsByTag.get(3).text();
            String timeArrival = elementsByTag.get(6).text();
            java.util.Date departure = parseTimeStringToDate(timeDeparture, tourDate);
            java.util.Date arrival = parseTimeStringToDate(timeArrival, tourDate);
            if (departure.compareTo(arrival) > 0) {
                departure = new Date(departure.getTime() - MILLISECONDS_IN_DAY);
            }
            fillAllBusTransitSetters(busTransit, cityFromAndNameOfStation, cityToAndNameOfStation,
                    departure, arrival);
            busList.add(busTransit);
        }
    }

    private java.util.Date parseTimeStringToDate(String dateF, java.util.Date tourDate) {
        long dateFH = Integer.parseInt(dateF.substring(0, 2)) * MILLISECONDS_IN_HOUR;
        long dateFM = Integer.parseInt(dateF.substring(3, 5)) * MILLISECONDS_IN_MINUTE;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(tourDate);
        calendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
        calendar.set(GregorianCalendar.MINUTE, 0);
        calendar.set(GregorianCalendar.SECOND, 0);
        calendar.set(GregorianCalendar.MILLISECOND, 0);
        java.util.Date newTourDate = calendar.getTime();
        java.util.Date departureTime = new Date(newTourDate.getTime() + dateFH + dateFM);
        if (departureTime.compareTo(tourDate) > 0){
            departureTime = new Date(departureTime.getTime() - MILLISECONDS_IN_DAY);
        }
        return departureTime;
    }

    private void fillAllBusTransitSetters(BusTransit busTransit, String cityFrom, String cityTo,
                                          java.util.Date departureTime, java.util.Date arrivalTime) {
        busTransit.setCityFrom(cityFrom);
        busTransit.setCityTo(cityTo);
        busTransit.setDepartureTime(departureTime);
        busTransit.setArrivalTime(arrivalTime);
    }

    public static void main(String[] args) {
        BusParser busParser = new BusParser("Київ", "Львів",
                              new Date(new GregorianCalendar(2014, 9, 25).getTime().getTime()));
        List<BusTransit> list = busParser.parse();
        for(int i = 0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
}

