package com.softserveinc.softtour.parsers;

import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Region;
import com.softserveinc.softtour.parsers.constants.StaticDataParserConstants;
import com.softserveinc.softtour.service.CountryService;
import com.softserveinc.softtour.service.HotelService;
import com.softserveinc.softtour.service.RegionService;
import com.softserveinc.softtour.util.StaticDataParserUrlGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Component
public class StaticDataParser implements StaticDataParserConstants {

    @Autowired
    CountryService countryService;

    @Autowired
    RegionService regionService;

    @Autowired
    HotelService hotelService;

    public void parse() {
        List<Element> countryList = getCountryData();
        for (Element countryEl : countryList) {
            String countryName = countryEl.text();
            long countryCode = Integer.parseInt(countryEl.attr(ATTR_VALUE));
            Country country = new Country(countryName, countryCode);
            countryService.save(country);


            List<Element> regionList = getRegionData(countryCode);
            for (int i = 1; i < regionList.size(); i++) {
                if (regionList.get(i).text().equals(TAL_DATA_REGION)) {
                    break;
                }
                String regionName = regionList.get(i).text();
                long regionCode = Integer.parseInt(regionList.get(i).attr(ATTR_VALUE));
                Region region = new Region(regionName, regionCode, country);
                regionService.save(region);


                List<Element> hotelList = getHotelData(countryCode, regionCode);
                for (int j = 1; j < hotelList.size(); j++) {
                    if (hotelList.get(j).text().equals(TAL_DATA_HOTEL)) {
                        break;
                    }
                    String hotelName = hotelList.get(j).text();
                    long hotelCode = Integer.parseInt(hotelList.get(j).attr(ATTR_VALUE));
                    Hotel hotel = new Hotel(hotelName, 0, 0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                            BigDecimal.ZERO, BigDecimal.ZERO, "", hotelCode, region);
                    hotelService.save(hotel);
                }
            }
        }
    }

    private Document connect(String url) {
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

    private List<Element> getCountryData(){
        String countryUrl = StaticDataParserUrlGenerator.createCountryUrl();
        Document countryDoc = connect(countryUrl);
        Element countryElement = countryDoc.getElementById(ID_ITT_COUNTRY);
        return countryElement.getElementsByTag(TAG_OPTION);
    }

    private List<Element> getRegionData(long countryCode){
        String regionUrl = StaticDataParserUrlGenerator.createRegionUrl(countryCode);
        Document regionDoc = connect(regionUrl);
        return regionDoc.getElementsByTag(TAG_OPTION);
    }

    private List<Element> getHotelData(long countryCode, long regionCode){
        String hotelUrl = StaticDataParserUrlGenerator.createHotelUrl(countryCode, regionCode);
        Document hotelDoc = connect(hotelUrl);
        return hotelDoc.getElementsByTag(TAG_OPTION);
    }
}
