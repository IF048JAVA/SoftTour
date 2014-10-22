package com.softserveinc.softtour.parsers;

import com.softserveinc.softtour.util.StaticDataHolder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StaticDataParser {
    private StaticDataHolder holder = new StaticDataHolder();
    private float loadPercent;

    public StaticDataHolder parse(){
        StaticDataParser parser = new StaticDataParser();
        String countryUrl = "http://module.ittour.com.ua/tour_search.jsx?id=5062D1884G6M7121819576&ver=1&type=2970";
        Document countryDoc = parser.connect(countryUrl);
        Element countryElement = countryDoc.getElementById("itt_country");
        List<Element> countryList = countryElement.getElementsByTag("option");
        float percentCountry = (float)100.0/countryList.size();
        for(Element country : countryList){
            String countryName = country.text();
            int countryCode = Integer.parseInt(country.attr("value"));
            System.out.println("country - " + countryName + " : " + countryCode);
            holder.saveCountry(countryCode, countryName);

            String regionUrl = "http://www.ittour.com.ua/tour_search.php?callback=jQuery17105734387715347111_1413973887053&" +
                    "module_type=tour_search&id=5062D1884G6M7121819576&ver=1&type=2970&theme=38&" +
                    "action=get_package_search_filtered_field&event_owner_level=1&country_id=" + countryCode;
            Document regionDoc = parser.connect(regionUrl);
            List<Element> regionList = regionDoc.getElementsByTag("option");
            float percentRegion = (float) percentCountry/regionList.size();
            for(int i = 1; i < regionList.size(); i++){
                if(regionList.get(i).text().equals("Все отели")){
                    break;
                }
                String regionName = regionList.get(i).text();
                int regionCode = Integer.parseInt(regionList.get(i).attr("value"));
                System.out.println("  region - " + regionName + " : " + regionCode);
                holder.saveRegion(countryCode, regionCode, regionName);

                String hotelUrl = regionUrl + "&hotel_rating_id=7+3+4+78+&event_owner_level=2&region_id="+ regionCode + "+";
                Document hotelDoc = parser.connect(hotelUrl);
                List<Element> hotelList = hotelDoc.getElementsByTag("option");
                float percentHotel = (float) percentRegion/hotelList.size();
                loadPercent += percentHotel;
                System.out.println(loadPercent);
                for(int j = 1; j < hotelList.size(); j++) {
                    if(hotelList.get(j).text().equals("Все города")){
                        break;
                    }
                    String hotelName = hotelList.get(j).text();
                    int hotelCode = Integer.parseInt(hotelList.get(j).attr("value"));
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
                    timeout(20000).
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
