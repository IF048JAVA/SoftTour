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
    private Properties countryProperties = new Properties();
    private String Url;
    private int page = 1;

    {
        try {
            InputStream inputCountry = this.getClass().
                    getResourceAsStream("/parser_properties/it_tour_country_parameters");
            countryProperties.load(new InputStreamReader(inputCountry, "UTF-8"));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public String createUrl(String country, int adults, int children, int priceFrom, int priceTo){
        StringBuilder stringBuilder = new StringBuilder(baseParams);
        stringBuilder.append("&country=").append(countryProperties.getProperty(country));
        stringBuilder.append("&adults=").append(adults);
        stringBuilder.append("&children=").append(children);
        stringBuilder.append("&price_from=").append(priceFrom);
        stringBuilder.append("&price_till=").append(priceTo);
        System.out.println(stringBuilder.toString());
        this.Url = stringBuilder.toString();
        return Url;
    }

    public String nextPage(){
        return new StringBuilder(Url).append("&page=").append(++page).toString();
    }
}
