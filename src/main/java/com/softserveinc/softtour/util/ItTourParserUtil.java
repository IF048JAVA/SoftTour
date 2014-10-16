package com.softserveinc.softtour.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ItTourParserUtil {
    private String baseParams = "http://www.ittour.com.ua/tour_search.php?callback=jQuery17108052812705282122_1413462622451&module_type=tour_search&id=5062D1884G6M7121819576&ver=1&type=2970&theme=38&action=package_tour_search&hotel_rating=7+3+4+78&food=496+388+498+512+560+1956&hotel=&region=&child_age=&package_tour_type=1&tour_kind=0&date_from=16.10.14&date_till=26.10.14&night_from=8&night_till=8&switch_price=USD&departure_city=0&items_per_page=100&module_location_url=http%3A%2F%2Ftyr.com.ua%2Ftours%2Fsearch.php&preview=1&_=1413462631538";
    private String hotelBaseParams = "http://www.ittour.com.ua/tour_search.php?" +
            "callback=jQuery17106546518665272743_1413444817014&module_type=tour_search" +
            "&id=5062D1884G6M7121819576&ver=1&type=2970&theme=38&action=get_package_tour_order_form";
    private Properties countryProperties = new Properties();
    private String Url;

    /*
    quiq search url
http://www.ittour.com.ua/tour_search.php?callback=jQuery17108052812705282122_1413462622451&module_type=tour_search&id=5062D1884G6M7121819576&ver=1&type=2970&theme=38&action=package_tour_search&hotel_rating=7+3+4+78&food=496+388+498+512+560+1956&hotel=&region=&child_age=&package_tour_type=1&tour_kind=0&date_from=16.10.14&date_till=26.10.14&night_from=8&night_till=8&switch_price=USD&departure_city=0&items_per_page=100&module_location_url=http%3A%2F%2Ftyr.com.ua%2Ftours%2Fsearch.php&preview=1&_=1413462631538
    * */

    {
        try {
            InputStream inputCountry = this.getClass().
                    getResourceAsStream("/parser_properties/it_tour_country_parameters");
            countryProperties.load(new InputStreamReader(inputCountry, "UTF-8"));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public String createQuickSearchUrl(String country, int adults, int children, int priceFrom, int priceTo, int pageNumber){
        StringBuilder stringBuilder = new StringBuilder(baseParams);
        stringBuilder.append("&country=").append(countryProperties.getProperty(country));
        stringBuilder.append("&adults=").append(adults);
        stringBuilder.append("&children=").append(children);
        stringBuilder.append("&price_from=").append(priceFrom);
        stringBuilder.append("&price_till=").append(priceTo);
        stringBuilder.append("&page=").append(pageNumber);
        System.out.println(stringBuilder.toString());
        this.Url = stringBuilder.toString();
        return Url;
    }

    public String hotelInfoUrl(String [] id){
        StringBuilder stringBuilder = new StringBuilder(hotelBaseParams);
        stringBuilder.append("&tour_id=").append(id[0]);
        stringBuilder.append("&sharding_rule_id=").append(id[1]);
        return stringBuilder.toString();
    }

}
