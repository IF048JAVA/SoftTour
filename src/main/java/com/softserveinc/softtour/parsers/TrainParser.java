package com.softserveinc.softtour.parsers;

import com.softserveinc.softtour.dto.TrainTransit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

public class TrainParser {
private static Map<String, String> urlParams = new HashMap<>();
private List<TrainTransit> trainList = new ArrayList<>();
private String cityFrom;
private String cityTo;
private java.sql.Date tourDate;

    static {
        urlParams.put("Львів", "2218161%2C2218092%2C2218543%2C2218081%2C2218074%2C2218058%2C2218215%2C2218921%2C2218203%2C2218000");
        urlParams.put("Київ", "739%2C2200008%2C2200007%2C2200006%2C2200005%2C2200004%2C2200003%2C2200002%2C2200001%2C2200009%2C3723");
        urlParams.put("Івано-Франківськ", "2218200");
    }

    public TrainParser(String cityFrom, String cityTo, java.sql.Date tourDate) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.tourDate = tourDate;
    }

    public List<TrainTransit> parse() throws IOException {
        String url = createURL(cityFrom, cityTo, tourDate);
        Document doc  = Jsoup.connect(url).get();
        Elements trains = doc.select("td");
        removeNotUsefulLines(trains);
        addTrainsToList(trains, cityFrom, cityTo, tourDate);
        return trainList;
    }

    private String createURL(String cityFrom, String cityTo, java.sql.Date tourDate) {
        StringBuffer url = new StringBuffer("http://www.uz.gov.ua/passengers/timetables_cis/?");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(tourDate);
        int dd = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        int mm = calendar.get(GregorianCalendar.MONTH) + 1;
        int yyyy = calendar.get(GregorianCalendar.YEAR);
        StringBuilder date = new StringBuilder();

        if(dd < 10){
            date.append("0").append(dd).append(".");
        } else {
            date.append(dd).append(".");
        }

        if(mm < 10){
            date.append("0").append(mm).append(".");
        } else {
            date.append(mm).append(".");
        }

        date.append(yyyy).append(".");
        url.append("from_station=").
                append(urlParams.get(cityFrom)).
                append("&to_station=").
                append(urlParams.get(cityTo)).
                append("&start_date=").
                append(date).
                append("&select_time=2&time_from=00&time_to=24").
                append("&by_route=Пошук");
        return url.toString();
    }

    private void removeNotUsefulLines(Elements raises) {
        for(int i = 0; i < 10; i++){
            raises.remove(0);
        }
    }

    private void addTrainsToList(Elements trains, String cityFrom, String cityTo, Date tourDate){
        int step = 10;
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
        for(int i = 0; i<trains.size(); i+=step){
            if(i > step){
                TrainTransit trainTransit = new TrainTransit();
                String stationFrom = trains.get(0+step).text();
                String stationTo = trains.get(1+step).text();
                String timeDep = trains.get(7+step).text();
                String arriv = trains.get(8+step).text();
                java.util.Date departure = parseTimeStringToDate(timeDep, tourDate);
                java.util.Date arrival = parseTimeStringToDate(arriv, tourDate);
                if (departure.compareTo(arrival) > 0) {
                    departure = new Date(departure.getTime() - 24 * 60 * 60 * 1000);
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
                    departure = new Date(departure.getTime() - 24 * 60 * 60 * 1000);
                }
                fillAllTrainTransitSetters(trainTransit, stationFrom, stationTo, departure, arrival);
                trainList.add(trainTransit);
            }
        }
        }

    private java.util.Date parseTimeStringToDate(String dateF, java.util.Date tourDate) {
        long dateFH = Integer.parseInt(dateF.substring(0, 2))*60*60*1000;
        long dateFM = Integer.parseInt(dateF.substring(3, 5))*60*1000;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(tourDate);
        java.util.Date newTourDate = calendar.getTime();
        java.util.Date departureTime = new java.util.Date(newTourDate.getTime() + dateFH + dateFM);
        if (departureTime.compareTo(tourDate) > 0){
            departureTime = new java.util.Date(departureTime.getTime() - 24*60*60*1000);
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



