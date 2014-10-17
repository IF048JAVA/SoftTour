package com.softserveinc.softtour.util;

import com.softserveinc.softtour.util.constants.ItTourParserUtilConstants;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ItTourParserUtil implements ItTourParserUtilConstants {
    private String baseParams = "http://www.ittour.com.ua/tour_search.php?callback=jQuery17108052812705282122_1413462622451&module_type=tour_search&id=5062D1884G6M7121819576&ver=1&type=2970&theme=38&action=package_tour_search&hotel_rating=7+3+4+78&food=496+388+498+512+560+1956&hotel=&region=&child_age=&package_tour_type=1&tour_kind=0&date_from=16.10.14&date_till=26.10.14&night_from=8&night_till=8&switch_price=USD&departure_city=0&items_per_page=100&module_location_url=http%3A%2F%2Ftyr.com.ua%2Ftours%2Fsearch.php&preview=1&_=1413462631538";
    private String hotelBaseParams = "http://www.ittour.com.ua/tour_search.php?" +
            "callback=jQuery17106546518665272743_1413444817014&module_type=tour_search" +
            "&id=5062D1884G6M7121819576&ver=1&type=2970&theme=38&action=get_package_tour_order_form";
    private Properties countryProperties = new Properties();
    private String Url;

    /*
    quiq search url
http://www.ittour.com.ua/tour_search.php?callback=jQuery17108052812705282122_1413462622451&module_type=tour_search&id=5062D1884G6M7121819576&ver=1&type=2970&theme=38&action=package_tour_search&hotel_rating=7+3+4+78&food=496+388+498+512+560+1956&hotel=&region=&child_age=&package_tour_type=1&tour_kind=0&date_from=16.10.14&date_till=26.10.14&night_from=8&night_till=8&switch_price=USD&departure_city=0&items_per_page=100&module_location_url=http%3A%2F%2Ftyr.com.ua%2Ftours%2Fsearch.php&preview=1&_=1413462631538
action=package_tour_search&hotel_rating=7+3+4+78&food=496+388+498+512+560+1956&hotel=&region=&child_age=&
package_tour_type=1&date_from=16.10.14&date_till=26.10.14&night_from=8&night_till=8&departure_city=0&

    * */

    {
        try {
            InputStream inputCountryProperties = this.getClass().
                    getResourceAsStream(PROPERTIES_FILE_PASS);
            countryProperties.load(new InputStreamReader(inputCountryProperties, UTF_8));
        }catch (IOException e){
            //TODO improve handled exception
            System.out.println(e.getMessage());
        }
    }

    private static StringBuilder getBaseParameters(){
        StringBuilder baseParamBuilder = new StringBuilder(HTTP).append(ASK).
            append(CALLBACK_PARAM).append(EQV).append(CALLBACK_VALUE).append(AMP).
            append(MODULE_TYPE_PARAM).append(EQV).append(MODULE_TYPE_VALUE).append(AMP).
            append(ID_PARAM).append(EQV).append(ID_VALUE).append(AMP).
            append(VER_PARAM).append(EQV).append(VER_VALUE).append(AMP).
            append(TYPE_PARAM).append(EQV).append(TYPE_VALUE).append(AMP).
            append(THEME_PARAM).append(EQV).append(THEME_VALUE).append(AMP).
            append(ACTION_PARAM).append(EQV).append(ACTION_VALUE).append(AMP).
            append(TOUR_KIND_PARAM).append(EQV).append(TOUR_KIND_VALUE).append(AMP).
            append(SWITCH_PRICE_PARAM).append(EQV).append(SWITCH_PRICE_VALUE).append(AMP).
            append(PREVIEW_PARAM).append(EQV).append(PREVIEW_VALUE).append(AMP).
            append(ITEMS_PER_PAGE_PARAM).append(EQV).append(ITEMS_PER_PAGE_VALUE).append(AMP);
        return baseParamBuilder;
    }

    public String createQuickSearchUrl(String country, int adults, int children, int priceFrom, int priceTo, int pageNumber){
        StringBuilder quickSearchBuilder = new StringBuilder(getBaseParameters()).
        append(COUNTRY_PARAM).append(EQV).append(countryProperties.getProperty(country)).append(AMP).
        append(ADULTS_PARAM).append(EQV).append(adults).append(AMP).
        append(CHILDREN_PARAM).append(EQV).append(children).append(AMP).
        append(PRICE_FROM_PARAM).append(EQV).append(priceFrom).append(AMP).
        append(PRICE_TILL_PARAM).append(EQV).append(priceTo).append(AMP).
        append(PAGE_NUMBER_PARAM).append(EQV).append(pageNumber).append(AMP).
        append(HOTEL_RATING_PARAM).append(EQV).append(DEFAULT_HOTEL_RATING_VALUE).append(AMP).
        append(FOOD_PARAM).append(EQV).append(DEFAULT_FOOD_VALUE).append(AMP).
        append(NIGHTS_FROM_PARAM).append(EQV).append(DEFAULT_NIGHTS_VALUE).append(AMP).
        append(NIGHTS_TILL_PARAM).append(EQV).append(DEFAULT_NIGHTS_VALUE).append(AMP).
        append(DEPARTURE_CITY_PARAM).append(EQV).append(DEFAULT_DEPARTURE_CITY_VALUE).append(AMP).
        append(PACKAGE_TOUR_TYPE_PARAM).append(EQV).append(PACKAGE_TOUR_TYPE_VALUE);
        return quickSearchBuilder.toString();
    }

    public String createHotelInfoUrl(String[] id){
        StringBuilder stringBuilder = new StringBuilder(getBaseParameters()).
        append(TOUR_ID_PARAM).append(EQV).append(id[0]).append(AMP).
        append(SHARDING_RULE_ID_PARAM).append(EQV).append(id[1]);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ItTourParserUtil util = new ItTourParserUtil();
        String url = util.createQuickSearchUrl("CO", 5, 5, 333, 444, 3);
        System.out.println(url);
    }

}
