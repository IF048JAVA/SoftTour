package com.softserveinc.softtour.parsers.impl;

import com.softserveinc.softtour.dto.BusRoute;
import com.softserveinc.softtour.parsers.constants.BusParserConstants;
import com.softserveinc.softtour.util.BusParserUrlGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BusParser implements BusParserConstants {
    private List<BusRoute> busList = new ArrayList<>();
    private String cityFrom;
    private String cityTo;
    private String tourDate;
    private BusParserUrlGenerator urlGenerator;
    private String url;

    public BusParser(String cityFrom, String cityTo, String tourDate) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.tourDate = tourDate;
        this.urlGenerator = new BusParserUrlGenerator();
        this.url = urlGenerator.createSearchUrl(cityFrom, cityTo, tourDate);
    }

    public List<BusRoute> parse() {
        Document document = connect(url);
        addBuses(document);
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
            /*
    private String id;
    private String departureCity;
    private String arrivalCity;
    private String departureDate;
    private String departureTime;
    private String arrivalTime;
    private String price;
            * */
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

    public static void main(String[] args) {
        BusParser busParser = new BusParser("Київ", "Львів", "22.10.14");
        List<BusRoute> list = busParser.parse();
        for(BusRoute route : list){
            System.out.println(route);
        }
    }
}

