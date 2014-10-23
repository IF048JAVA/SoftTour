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

public class BusParser implements ParsersConstants {
    private List<BusRoute> busList;
    private String cityFrom;
    private String cityTo;
    private String departureDate;
    private String departureTime;
    private Date departureDateTime;
    private SimpleDateFormat simpleDateFormat;

    public BusParser(String cityFrom, String cityTo, String departureDate, String departureTime) {
        this.busList = new ArrayList<>();
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        String dateAndTime = new StringBuilder(departureDate).append(".").append(departureTime).toString();
        try {
            this.departureDateTime = new SimpleDateFormat(BUS_DAY_FORMAT).parse(dateAndTime);
        }catch (ParseException e){
            System.out.println(e.getMessage());
        }
        simpleDateFormat = new SimpleDateFormat(DAY_FORMAT);
    }

    private String generateTodayUrl(){
        Date todayDate = new Date(departureDateTime.getTime() - THREE_HOURS_IN_MILLISECONDS);
        String resultDateString = simpleDateFormat.format(todayDate);
        BusParserUrlGenerator generator = new BusParserUrlGenerator();
        String url = generator.createSearchUrl(cityFrom, cityTo, resultDateString);
        return url;
    }

    private String generateYesterdayUrl(){
        Date yesterdayDate = new Date(departureDateTime.getTime() - TWENTY_SEVEN_HOURS_IN_MILLISECONDS);
        String resultDateString = simpleDateFormat.format(yesterdayDate);
        BusParserUrlGenerator generator = new BusParserUrlGenerator();
        String url = generator.createSearchUrl(cityFrom, cityTo, resultDateString);
        return url;
    }

    public List<BusRoute> parse() {
        String todayUrl = generateTodayUrl();
        String yesterdayUrl = generateYesterdayUrl();
        Document document = connect(todayUrl);
        addBuses(document);
        document = connect(yesterdayUrl);
        addBuses(document);
        removeWasteRouts();
        return busList;
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
        List<Element> elementListSmall = document.getElementsByTag(TAG_TR);
        for(int i = 7; i < elementListSmall.size() - 5; i++){
            /*
0 <td>
 <div style="display: none;">
  <span class="date_dep">22.10.14</span>
  <span class="bs_code">306100</span>
  <span class="local_point_from">306100</span>
  <span class="local_point_to">460100</span>
 </div> <input type="radio" name="round_num" value="1385" /> </td>
1 <td align="center" nowrap="yes">22.10.14</td>
2 <td align="center" style="padding-left: 8px; padding-right: 8px;" nowrap="yes"> <small style="border-bottom: 1px dashed" title="ЖД (север) - вул.С.Петлюри (Комінтерну), 32; Телефон: 044-2293604,063-339-5353, 097-953-5353,099-444-5353"> КИЇВ АС-ЗЛВ <span class="ui-icon ui-icon-info" style="display: inline-block"> </span> </small> <b>11:10</b> </td>
3 <td nowrap="nowrap"> 20:50 <small>ЛЬВІВ</small> </td>
4 <td align="center">243.55</td>
5 <td align="center">562</td>
6 <td align="center">100%</td>
7 <td> <small style="font-size: 75%;"> 1385 КИЇВ АС-ЗЛВ - ЛЬВІВ </small> </td>
8 <td><small>МЕРСЕДЕС-303</small></td>
9 <td align="center"> <img border="0" title="Качество связи: 100%" src="http://ticket.bus.com.ua/static/images/m-blue.png" /> </td>
10 <td> </td>
             */
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

            BusRoute route = new BusRoute(id, departureCity, arrivalCity, departureDate, departureTime, arrivalTime,
                                          price);
            busList.add(route);
         }
    }

    private void removeWasteRouts(){
        Date marginalDate = new Date(departureDateTime.getTime() - THREE_HOURS_IN_MILLISECONDS);
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
        for(int i = 0; i < busList.size(); i++){
            String dateAndTime = new StringBuilder(busList.get(i).getDepartureDate()).append(".").
                                 append(busList.get(i).getDepartureTime()).toString();
            Date routeDate = null;
            try {
                routeDate = dateFormat.parse(dateAndTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int compare = marginalDate.compareTo(routeDate);
            if(compare < 0){
                busList.remove(busList.get(i));
                i--;
            }
        }
    }

    public static void main(String[] args) {
        BusParser busParser = new BusParser("Київ", "Львів", "2014-11-01", "23:00");
        List<BusRoute> list = busParser.parse();
        for(BusRoute route : list){
            System.out.println(route);
        }
    }
}

