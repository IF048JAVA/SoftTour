package com.softserveinc.softtour.parsers;

import com.softserveinc.softtour.dto.BusTransit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;

public class BusParser {
    private List<BusTransit> busList;

    public BusParser() {
        this.busList = new ArrayList<>();
    }

    public List<BusTransit> parse(String cityFrom, String cityTo, Date tourDate) throws IOException {
        String url = createURL(cityFrom, cityTo);
        Document doc  = Jsoup.connect(url).get();
        Elements buses = doc.select("tr[class~=aslist(?)]");
        removeNotUsefulLines(buses);
        addBusesToList(buses, cityFrom, cityTo, tourDate);
        return busList;
    }

    private String createURL(String cityFrom, String cityTo) {
        StringBuffer url = new StringBuffer("http://bus.com.ua/cgi-bin/poshuk?");
        switch (cityFrom) {
            case "Львів": {
                url.append("fp=UA4610100000&");
                break;
            } case "Київ": {
                url.append("fp=UA8000000000&");
                break;
            } case "Івано-Франківськ": {
                url.append("fp=UA2610100000&");
                break;
            } default: {
                System.err.print("Error!");
                break;
            }
        }
        switch (cityTo) {
            case "Львів": {
                url.append("tp=UA4610100000&Go=3");
                break;
            } case "Київ": {
                url.append("tp=UA8000000000&Go=3");
                break;
            } case "Івано-Франківськ": {
                url.append("tp=UA2610100000&Go=3");
                break;
            } default: {
                System.err.print("Error!");
                break;
            }
        }
        return url.toString();
    }

    private void removeNotUsefulLines(Elements raises) {
        Iterator<Element> iterator = raises.iterator();
        while (iterator.hasNext()){
            String regularity = iterator.next().text();
            if(regularity.contains("Рейси з регулярністю від")){
                iterator.remove();
            }
        }
    }

    private void addBusesToList(Elements buses, String cityFrom, String cityTo, Date tourDate){
        for(Element element : buses) {
            BusTransit busTransit = new BusTransit();
            Elements elementsByTag = element.getElementsByTag("td");
            String cityFromAndNameOfStation = elementsByTag.get(2).text();
            String cityToAndNameOfStation = elementsByTag.get(5).text();
            String timeDeparture = elementsByTag.get(3).text();
            String timeArrival = elementsByTag.get(6).text();
            Date departure = parseTimeStringToDate(timeDeparture, tourDate);
            Date arrival = parseTimeStringToDate(timeArrival, tourDate);
            if (departure.compareTo(arrival) > 0) {
                departure = new Date(departure.getTime() - 24 * 60 * 60 * 1000);
            }
            int price = countPrice(cityFrom, cityTo);
            fillAllBusTransitSetters(busTransit, cityFromAndNameOfStation, cityToAndNameOfStation,
                    departure, arrival, price);
            busList.add(busTransit);
        }
    }

    private Date parseTimeStringToDate(String dateF, Date tourDate) {
        long dateFH = Integer.parseInt(dateF.substring(0, 2))*60*60*1000;
        long dateFM = Integer.parseInt(dateF.substring(3, 5))*60*1000;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(tourDate);
        calendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
        calendar.set(GregorianCalendar.MINUTE, 0);
        calendar.set(GregorianCalendar.SECOND, 0);
        calendar.set(GregorianCalendar.MILLISECOND, 0);
        Date newTourDate = calendar.getTime();
        Date departureTime = new Date(newTourDate.getTime() + dateFH + dateFM);
        if (departureTime.compareTo(tourDate) > 0){
            departureTime = new Date(departureTime.getTime() - 24*60*60*1000);
        }
        return departureTime;
    }

    private int countPrice(String cityFrom, String cityTo) {
        if ((cityFrom.equals("Львів") && cityTo.equals("Івано-Франківськ")) ||
                (cityFrom.equals("Івано-Франківськ") && cityTo.equals("Львів"))) {
            return 70;
        } else {
            return 230;
        }
    }

    private void fillAllBusTransitSetters(BusTransit busTransit, String cityFrom, String cityTo,
                                          Date departureTime, Date arrivalTime, int price) {
        busTransit.setCityFrom(cityFrom);
        busTransit.setCityTo(cityTo);
        busTransit.setDepartureTime(departureTime);
        busTransit.setArrivalTime(arrivalTime);
        busTransit.setPrice(price);
    }
}

