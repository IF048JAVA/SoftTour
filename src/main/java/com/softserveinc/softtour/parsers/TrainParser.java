package com.softserveinc.softtour.parsers;

import com.softserveinc.softtour.dto.TrainTransit;
import com.softserveinc.softtour.parsers.constants.TrainParserConstants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.*;

public class TrainParser implements TrainParserConstants {
    private static Properties trainParserParams = new Properties();
    private List<TrainTransit> trainList = new ArrayList<>();
    private String cityFrom;
    private String cityTo;
    private java.sql.Date tourDate;

    static {
        try {
            InputStream inputParams = TrainParser.class.getClass().getResourceAsStream(RESOURCE_PATH_PARAMS);
            trainParserParams.load(new InputStreamReader(inputParams, DEFAULT_CHARSET));
        }catch (IOException e){
            System.out.println(e.getStackTrace());
        }
    }

    public TrainParser(String cityFrom, String cityTo, java.sql.Date tourDate) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.tourDate = tourDate;
    }

    public List<TrainTransit> parse() throws IOException {
        String url = createURL(cityFrom, cityTo, tourDate);
        Document doc  = Jsoup.connect(url).get();
        Elements trains = doc.select(TRAIN_SELECT);
        removeRedundantLines(trains);
        addTrainsToList(trains, cityFrom, cityTo, tourDate);
        return trainList;
    }

    private String createURL(String cityFrom, String cityTo, java.sql.Date tourDate) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(tourDate);
        int dd = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        int mm = calendar.get(GregorianCalendar.MONTH) + 1;
        int yyyy = calendar.get(GregorianCalendar.YEAR);
        StringBuilder date = new StringBuilder();

        if(dd < 10){
            date.append(ZERO_MARK).append(dd).append(POINT_MARK);
        } else {
            date.append(dd).append(POINT_MARK);
        }
        if(mm < 10){
            date.append(ZERO_MARK).append(mm).append(POINT_MARK);
        } else {
            date.append(mm).append(POINT_MARK);
        }
        date.append(yyyy);
        StringBuilder url = new StringBuilder(URL);
        url.append(QUESTION_MARK).
            append(PARAM_CITY_FROM).append(EQUAL_MARK).append(trainParserParams.getProperty(cityFrom)).
            append(AMPERSAND_MARK).
            append(PARAM_CITY_TO).append(EQUAL_MARK).append(trainParserParams.getProperty(cityTo)).
            append(AMPERSAND_MARK).
            append(PARAM_DATE).append(EQUAL_MARK).append(date).
            append(AMPERSAND_MARK).
            append(PARAM_TIME).append(EQUAL_MARK).append(VALUE_TIME).
            append(AMPERSAND_MARK).
            append(PARAM_TIME_FROM).append(EQUAL_MARK).append(VALUE_TIME_FROM).
            append(PARAM_TIME_TO).append(EQUAL_MARK).append(VALUE_TIME_TO).
            append(AMPERSAND_MARK).
            append(PARAM_SEARCH).append(EQUAL_MARK).append(VALUE_SEARCH);
        return url.toString();
    }

    private void removeRedundantLines(Elements raises) {
        for(int i = 0; i < 10; i++){
            raises.remove(0);
        }
    }

    private void addTrainsToList(Elements trains, String cityFrom, String cityTo, Date tourDate){
        /*
 0 : Київ-Пас.
1 : Львів
2 : 9:14
3 : 111О
4 : Харків-Пас - Львів
5 :
6 : 03:48
7 : 04:08
8 : 13:22
9 :

        * */
        for(int i = 0; i<trains.size(); i+=FOR_CYCLE_STEP){
            if(i > FOR_CYCLE_STEP){
                TrainTransit trainTransit = new TrainTransit();
                String stationFrom = trains.get(0+FOR_CYCLE_STEP).text();
                String stationTo = trains.get(1+FOR_CYCLE_STEP).text();
                String timeDep = trains.get(7+FOR_CYCLE_STEP).text();
                String arriv = trains.get(8+FOR_CYCLE_STEP).text();
                java.util.Date departure = parseTimeStringToDate(timeDep, tourDate);
                java.util.Date arrival = parseTimeStringToDate(arriv, tourDate);
                if (departure.compareTo(arrival) > 0) {
                    departure = new Date(departure.getTime() - MILLISECONDS_IN_DAY);
                }
                fillAllTrainTransitSetters(trainTransit, stationFrom, stationTo, departure, arrival);
                trainList.add(trainTransit);
            } else {
                TrainTransit trainTransit = new TrainTransit();
                String stationFrom = trains.get(0).text();
                String stationTo = trains.get(1).text();
                String timeDep = trains.get(7).text();
                String arriv = trains.get(8).text();
                java.util.Date departure = parseTimeStringToDate(timeDep, tourDate);
                java.util.Date arrival = parseTimeStringToDate(arriv, tourDate);
                if (departure.compareTo(arrival) > 0) {
                    departure = new Date(departure.getTime() - MILLISECONDS_IN_DAY);
                }
                fillAllTrainTransitSetters(trainTransit, stationFrom, stationTo, departure, arrival);
                trainList.add(trainTransit);
            }
        }
        }

    private java.util.Date parseTimeStringToDate(String dateF, java.util.Date tourDate) {
        long dateFH = Integer.parseInt(dateF.substring(0, 2)) * MILLISECONDS_IN_HOUR;
        long dateFM = Integer.parseInt(dateF.substring(3, 5)) * MILLISECONDS_IN_MINUTE;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(tourDate);
        java.util.Date newTourDate = calendar.getTime();
        java.util.Date departureTime = new java.util.Date(newTourDate.getTime() + dateFH + dateFM);
        if (departureTime.compareTo(tourDate) > 0){
            departureTime = new java.util.Date(departureTime.getTime() - MILLISECONDS_IN_DAY);
        }
        return departureTime;
    }

    private void fillAllTrainTransitSetters(TrainTransit trainTransit, String cityFrom, String cityTo,
                                            java.util.Date departureTime, java.util.Date arrivalTime) {
        trainTransit.setCityFrom(cityFrom);
        trainTransit.setCityTo(cityTo);
        trainTransit.setDepartureTime(departureTime);
        trainTransit.setArrivalTime(arrivalTime);
    }

    public static void main(String[] args) throws IOException{
        TrainParser trainParser = new TrainParser("Київ", "Львів", new Date(new GregorianCalendar(2014, 9, 25).getTime().getTime()));
        List<TrainTransit> list = trainParser.parse();
        for(int i = 0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
}



