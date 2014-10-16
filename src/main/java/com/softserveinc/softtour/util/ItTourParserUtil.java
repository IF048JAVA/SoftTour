package com.softserveinc.softtour.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ItTourParserUtil {
    private String baseParams = "http://www.ittour.com.ua/tour_search.php?" +
            "callback=jQuery17109648473216220737_1412803322658&id=5062D1884G6M7121819576&ver=1&type=2970&theme=38" +
            "&action=package_tour_search&package_tour_type=0&tour_kind=0&default_form_select=1" +
            "&items_per_page=100&night_from=8&night_till=8&preview=1";
    private String hotelBaseParams = "http://www.ittour.com.ua/tour_search.php?" +
            "callback=jQuery17106546518665272743_1413444817014&module_type=tour_search" +
            "&id=5062D1884G6M7121819576&ver=1&type=2970&theme=38&action=get_package_tour_order_form";
    private Properties countryProperties = new Properties();
    private String Url;

    {
        try {
            InputStream inputCountry = this.getClass().
                    getResourceAsStream("/parser_properties/it_tour_country_parameters");
            countryProperties.load(new InputStreamReader(inputCountry, "UTF-8"));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public String createUrl(String country, int adults, int children, int priceFrom, int priceTo, int pageNumber){
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
