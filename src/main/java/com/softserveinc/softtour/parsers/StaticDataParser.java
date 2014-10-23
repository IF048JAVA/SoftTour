package com.softserveinc.softtour.parsers;

import com.softserveinc.softtour.parsers.constants.ParsersConstants;
import com.softserveinc.softtour.util.StaticDataHolder;
import com.softserveinc.softtour.util.StaticDataParserUrlGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StaticDataParser implements ParsersConstants {
    private StaticDataParserUrlGenerator generator = new StaticDataParserUrlGenerator();
    private StaticDataHolder holder = new StaticDataHolder();
    private float loadPercent;

    public StaticDataHolder parse(){
        StaticDataParser parser = new StaticDataParser();
        String countryUrl = generator.createCountryUrl();
        Document countryDoc = parser.connect(countryUrl);
        Element countryElement = countryDoc.getElementById(ID_COUNTRY);
        List<Element> countryList = countryElement.getElementsByTag(TAG_OPTION);
        float percentCountry = (float)100.0/countryList.size();
        for(Element country : countryList){
            String countryName = country.text();
            int countryCode = Integer.parseInt(country.attr(ATTR_VALUE));
            System.out.println("country - " + countryName + " : " + countryCode);
            holder.saveCountry(countryCode, countryName);

            String regionUrl = generator.createRegionUrl(countryCode);

            Document regionDoc = parser.connect(regionUrl);
            List<Element> regionList = regionDoc.getElementsByTag(TAG_OPTION);
            float percentRegion = (float) percentCountry/regionList.size();
            for(int i = 1; i < regionList.size(); i++){
                if(regionList.get(i).text().equals(ALL_HOTELS)){
                    break;
                }
                String regionName = regionList.get(i).text();
                int regionCode = Integer.parseInt(regionList.get(i).attr(ATTR_VALUE));
                System.out.println("  region - " + regionName + " : " + regionCode);
                holder.saveRegion(countryCode, regionCode, regionName);

                String hotelUrl = generator.createHotelUrl(countryCode, regionCode);
                Document hotelDoc = parser.connect(hotelUrl);
                List<Element> hotelList = hotelDoc.getElementsByTag(TAG_OPTION);
                float percentHotel = (float) percentRegion/hotelList.size();
                loadPercent += percentHotel;
                System.out.println(loadPercent);
                for(int j = 1; j < hotelList.size(); j++) {
                    if(hotelList.get(j).text().equals(ALL_CITIES)){
                        break;
                    }
                    String hotelName = hotelList.get(j).text();
                    int hotelCode = Integer.parseInt(hotelList.get(j).attr(ATTR_VALUE));
                    System.out.println("    hotel - " + hotelName + " : " + hotelCode);
                    holder.saveHotel(regionCode, hotelCode, hotelName);
                }
            }
        }
        return holder;
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
        String tourPage = doc.replace("\\", "");
        Document document = Jsoup.parse(tourPage);
        return document;
    }

    public static void main(String[] args) {
        StaticDataParser parser = new StaticDataParser();
        StaticDataHolder dataHolder = parser.parse();
        Map<Integer, String> countryMap = dataHolder.getCountryMap();
        Map<Integer, String> regionMap = dataHolder.getRegionMap();
        Map<Integer, String> hotelMap = dataHolder.getHotelMap();
        Map<Integer, Integer> regionCountryRelatedMap = dataHolder.getRegionCountryRelatedMap();
        Map<Integer, Integer> hotelRegionRelatedMap = dataHolder.getHotelRegionRelatedMap();
    }
}
