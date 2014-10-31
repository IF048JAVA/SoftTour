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

/**
 * This class parse all countries, regions, hotels (names and codes) and save them to database.
 * The data source is site: http://www.ittour.com.ua
 */
@Component
public class StaticDataParser implements StaticDataParserConstants {

    @Autowired
    CountryService countryService;

    @Autowired
    RegionService regionService;

    @Autowired
    HotelService hotelService;

    /**
     * This method finds all countries, regions, hotels and save their data to database.
     */
    public boolean parse() {

        /**
         * This block of code finds all countries and save them to database.
         */
        List<Element> countryList = getCountryData();
        for (Element countryEl : countryList) {
            String countryName = countryEl.text();
            long countryCode = Integer.parseInt(countryEl.attr(ATTR_VALUE));

            Country countryDB = countryService.findByItTourId(countryCode);
            Country country = new Country(countryName, countryCode);

            if (countryDB == null) {
                countryService.save(country);
                System.out.println("Country " + countryName + " was saved");
            } else {
                System.out.println("Country " + countryName + " already exist");
            }

            /**
             * This block of code finds all country regions and save them to database.
             * There are tal data in regionList, that can't be exclude.
             * So, block if check is this data start to be tal. If it is, iterating will be stop.
             */
            List<Element> regionList = getRegionData(countryCode);
            for (int i = 1; i < regionList.size(); i++) {
                Element regionEl = regionList.get(i);
                String regionName = regionEl.text();
                if (regionName.equals(TAL_DATA_REGION)) {
                    break;
                }

                long regionCode = Integer.parseInt(regionEl.attr(ATTR_VALUE));

                Region regionDB = regionService.findByItTourId(regionCode);
                Region region = new Region(regionName, regionCode, country);

                if (regionDB == null) {
                    regionService.save(region);
                    System.out.println("Region " + regionName + " was saved");
                } else {
                    System.out.println("Region " + regionName + " already exist");
                }

                /**
                 * This block of code finds all region hotels and save them to database.
                 * There are tal data in hotelList, that can't be exclude.
                 * So, block if check is this data start to be tal. If it is, iterating will be stop.
                 */
                List<Element> hotelList = getHotelData(countryCode, regionCode);
                for (int j = 1; j < hotelList.size(); j++) {
                    Element hotelEl = hotelList.get(j);
                    String hotelName = hotelEl.text();
                    if (hotelName.equals(TAL_DATA_HOTEL)) {
                        break;
                    }
                    long hotelCode = Integer.parseInt(hotelEl.attr(ATTR_VALUE));

                    Hotel hotelDB = hotelService.findByItTourId(hotelCode);
                    Hotel hotel = new Hotel(hotelName, 0, 0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                            BigDecimal.ZERO, BigDecimal.ZERO, "", hotelCode, region);

                    if (hotelDB == null) {
                        hotelService.save(hotel);
                        System.out.println("Hotel " + hotelName + " was saved");
                    } else {
                        System.out.println("Hotel " + hotelName + " already exist");
                    }
                }
            }
        }

        return true;
    }

    /**
     * @param url - web-page's url
     * @return web-page, encapsulated in Jsoup object Document
     */
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

        /**
         * Page is unparseable. Removing one backslash solve this problem.
         */
        String tourPage = doc.replace("\\", "");
        Document document = Jsoup.parse(tourPage);
        return document;
    }

    /**
     * This method create url, connect and get countries from web-page.
     *
     * @return Encapsulated in Jsoup object Element list data countries.
     */
    private List<Element> getCountryData() {
        String countryUrl = StaticDataParserUrlGenerator.createCountryUrl();
        Document countryDoc = connect(countryUrl);
        Element countryElement = countryDoc.getElementById(ID_ITT_COUNTRY);
        return countryElement.getElementsByTag(TAG_OPTION);
    }

    /**
     * This method create url, connect and get regions from web-page.
     *
     * @return Encapsulated in Jsoup object Element list data regions.
     */
    private List<Element> getRegionData(long countryCode) {
        String regionUrl = StaticDataParserUrlGenerator.createRegionUrl(countryCode);
        Document regionDoc = connect(regionUrl);
        return regionDoc.getElementsByTag(TAG_OPTION);
    }

    /**
     * This method create url, connect and get hotels from web-page.
     *
     * @return Encapsulated in Jsoup object Element list data hotels.
     */
    private List<Element> getHotelData(long countryCode, long regionCode) {
        String hotelUrl = StaticDataParserUrlGenerator.createHotelUrl(countryCode, regionCode);
        Document hotelDoc = connect(hotelUrl);
        return hotelDoc.getElementsByTag(TAG_OPTION);
    }

    @Override
    public String toString() {
        return "Static data parser. Finds all countries, region, hotels (name and id) and save them to database." +
                " Source: http://www.ittour.com.ua";
    }
}
