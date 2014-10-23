package com.softserveinc.softtour.parsers;

import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Region;
import com.softserveinc.softtour.service.CountryService;
import com.softserveinc.softtour.service.HotelService;
import com.softserveinc.softtour.service.RegionService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Component
public class StaticDataParser {

    @Autowired
    CountryService countryService;

    @Autowired
    RegionService regionService;

    @Autowired
    HotelService hotelService;

    public void parse() {

        StaticDataParser parser = new StaticDataParser();
        String countryUrl = "http://module.ittour.com.ua/tour_search.jsx?id=5062D1884G6M7121819576&ver=1&type=2970";
        Document countryDoc = parser.connect(countryUrl);
        Element countryElement = countryDoc.getElementById("itt_country");
        List<Element> countryList = countryElement.getElementsByTag("option");

        for (Element country : countryList) {

            String countryName = country.text();
            long countryCode = Integer.parseInt(country.attr("value"));

            Country newCountry = new Country(countryName, countryCode);
            countryService.save(newCountry);

            String regionUrl = "http://www.ittour.com.ua/tour_search.php?callback=jQuery17105734387715347111_1413973887053&" +
                    "module_type=tour_search&id=5062D1884G6M7121819576&ver=1&type=2970&theme=38&" +
                    "action=get_package_search_filtered_field&event_owner_level=1&country_id=" + countryCode;
            Document regionDoc = parser.connect(regionUrl);
            List<Element> regionList = regionDoc.getElementsByTag("option");

            for (int i = 1; i < regionList.size(); i++) {
                if (regionList.get(i).text().equals("Все отели")) {
                    break;
                }

                String regionName = regionList.get(i).text();
                long regionCode = Integer.parseInt(regionList.get(i).attr("value"));

                Region newRegion = new Region(regionName, regionCode, newCountry);
                regionService.save(newRegion);

                String hotelUrl = regionUrl + "&hotel_rating_id=7+3+4+78+&event_owner_level=2&region_id=" + regionCode + "+";
                Document hotelDoc = parser.connect(hotelUrl);
                List<Element> hotelList = hotelDoc.getElementsByTag("option");

                for (int j = 1; j < hotelList.size(); j++) {
                    if (hotelList.get(j).text().equals("Все города")) {
                        break;
                    }
                    String hotelName = hotelList.get(j).text();

                    long hotelCode = Integer.parseInt(hotelList.get(j).attr("value"));

                    Hotel newHotel = new Hotel(hotelName, 0, 0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                            BigDecimal.ZERO, BigDecimal.ZERO, "", hotelCode, newRegion);
                    hotelService.save(newHotel);

                }
            }
        }
    }


    private Document connect(String url) {

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
}
