package com.softserveinc.softtour.util;

import com.softserveinc.softtour.util.constants.ItTourParserUrlGeneratorConstants;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ItTourParserUrlGenerator implements ItTourParserUrlGeneratorConstants {
    private Properties countryProperties = new Properties();
    private Properties regionProperties = new Properties();

    private void loadCountryProperties(){
        try {
            InputStream inputCountryProperties = this.getClass().
                    getResourceAsStream(COUNTRY_PROPERTIES_FILE_PASS);
            countryProperties.load(new InputStreamReader(inputCountryProperties, UTF_8));
        }catch (IOException e){
            //TODO improve handled exception
            System.out.println(e.getMessage());
        }
    }

    private void loadRegionProperties(){
        try {
            InputStream inputRegionProperties = this.getClass().
                    getResourceAsStream(REGION_PROPERTIES_FILE_PASS);
            regionProperties.load(new InputStreamReader(inputRegionProperties, UTF_8));
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

    public String createQuickSearchUrl(String country, int adults, int children, int priceFrom, int priceTo,
                                       int pageNumber){
        loadCountryProperties();
        StringBuilder quickSearchBuilder = new StringBuilder(getBaseParameters()).
        append(COUNTRY_PARAM).append(EQV).append(countryProperties.getProperty(country)).append(AMP).
        append(HOTEL_RATING_PARAM).append(EQV).append(DEFAULT_HOTEL_RATING_VALUE).append(AMP).
        append(FOOD_PARAM).append(EQV).append(DEFAULT_FOOD_VALUE).append(AMP).
        append(ADULTS_PARAM).append(EQV).append(adults).append(AMP).
        append(CHILDREN_PARAM).append(EQV).append(children).append(AMP).
        append(NIGHTS_FROM_PARAM).append(EQV).append(DEFAULT_NIGHTS_VALUE).append(AMP).
        append(NIGHTS_TILL_PARAM).append(EQV).append(DEFAULT_NIGHTS_VALUE).append(AMP).
        append(PRICE_FROM_PARAM).append(EQV).append(priceFrom).append(AMP).
        append(PRICE_TILL_PARAM).append(EQV).append(priceTo).append(AMP).
        append(PAGE_NUMBER_PARAM).append(EQV).append(pageNumber).append(AMP).
        append(DEPARTURE_CITY_PARAM).append(EQV).append(DEFAULT_DEPARTURE_CITY_VALUE).append(AMP).
        append(PACKAGE_TOUR_TYPE_PARAM).append(EQV).append(PACKAGE_TOUR_TYPE_VALUE);
        return quickSearchBuilder.toString();
    }

    public String createAdvanceSearchUrl(String country, String region, int[] hotelStars, String[] food, int adults,
            int children, String dataFrom, String dataTill, int nightsFrom, int nightsTill, int priceFrom,
            int priceTo, int pageNumber){
        loadCountryProperties();
        loadRegionProperties();
        StringBuilder fullSearchBuilder = new StringBuilder(getBaseParameters()).
        append(COUNTRY_PARAM).append(EQV).append(countryProperties.getProperty(country)).append(AMP).
        append(REGION_PARAM).append(EQV).append(regionProperties.getProperty(region)).append(AMP).
        append(HOTEL_RATING_PARAM).append(EQV).append(hotelRating(hotelStars)).append(AMP).
        append(FOOD_PARAM).append(EQV).append(foodValue(food)).append(AMP).
        append(ADULTS_PARAM).append(EQV).append(adults).append(AMP).
        append(CHILDREN_PARAM).append(EQV).append(children).append(AMP).
        append(DATE_FROM_PARAM).append(EQV).append(dataFrom).append(AMP).
        append(DATE_TILL_PARAM).append(EQV).append(dataTill).append(AMP).
        append(NIGHTS_FROM_PARAM).append(EQV).append(nightsFrom).append(AMP).
        append(NIGHTS_TILL_PARAM).append(EQV).append(nightsTill).append(AMP).
        append(PRICE_FROM_PARAM).append(EQV).append(priceFrom).append(AMP).
        append(PRICE_TILL_PARAM).append(EQV).append(priceTo).append(AMP).
        append(PAGE_NUMBER_PARAM).append(EQV).append(pageNumber).append(AMP).
        append(DEPARTURE_CITY_PARAM).append(EQV).append(DEFAULT_DEPARTURE_CITY_VALUE).append(AMP).
        append(PACKAGE_TOUR_TYPE_PARAM).append(EQV).append(PACKAGE_TOUR_TYPE_VALUE);
        return fullSearchBuilder.toString();
    }

    private String hotelRating(int[] hotelStars){
        if(hotelStars == null || hotelStars.length == 0 || hotelStars.length == 4){
            return DEFAULT_HOTEL_RATING_VALUE;
        } else {
            StringBuilder hotelRatingBuilder = new StringBuilder();
            for(int i = 0; i < hotelStars.length; i++){
                switch (hotelStars[i]){
                    case 2: {
                        hotelRatingBuilder.append(7).append(PLUS);
                        break;
                    } case 3: {
                        hotelRatingBuilder.append(3).append(PLUS);
                        break;
                    } case 4: {
                        hotelRatingBuilder.append(4).append(PLUS);
                        break;
                    } case 5: {
                        hotelRatingBuilder.append(78).append(PLUS);
                        break;
                    } default: {
                        return DEFAULT_HOTEL_RATING_VALUE;
                    }
                }
            }
            // delete last mark "+"
            hotelRatingBuilder.delete(hotelRatingBuilder.length() - 1, hotelRatingBuilder.length());
            return hotelRatingBuilder.toString();
        }
    }

    private String foodValue(String[] food){
        if (food == null || food.length == 0 || food.length == 6){
            return DEFAULT_FOOD_VALUE;
        } else {
            StringBuilder foodBuilder = new StringBuilder();
            for(int i = 0; i < food.length; i++){
                switch (food[i]){
                    case "HB": {
                        foodBuilder.append(496).append(PLUS);
                        break;
                    } case "BB": {
                        foodBuilder.append(388).append(PLUS);
                        break;
                    } case "FB": {
                        foodBuilder.append(498).append(PLUS);
                        break;
                    } case "AI": {
                        foodBuilder.append(512).append(PLUS);
                        break;
                    } case "UAI": {
                        foodBuilder.append(560).append(PLUS);
                        break;
                    } case "RO": {
                        foodBuilder.append(1956).append(PLUS);
                        break;
                    } default: {
                        return DEFAULT_FOOD_VALUE;
                    }
                }
            }
            foodBuilder.delete(foodBuilder.length() - 1, foodBuilder.length()); // delete last mark "+"
            return foodBuilder.toString();
        }
    }

    public String createHotelInfoUrl(String[] id){
        StringBuilder stringBuilder = new StringBuilder(getBaseParameters()).
        append(TOUR_ID_PARAM).append(EQV).append(id[0]).append(AMP).
        append(SHARDING_RULE_ID_PARAM).append(EQV).append(id[1]);
        return stringBuilder.toString();
    }
}
