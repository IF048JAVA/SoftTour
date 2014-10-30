package com.softserveinc.softtour.util;

import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Region;
import com.softserveinc.softtour.util.constants.ItTourParserUrlGeneratorConstants;

import java.util.*;

public class ItTourParserUrlGenerator implements ItTourParserUrlGeneratorConstants {

    private static StringBuilder getBaseParameters(){
        StringBuilder baseParamBuilder = new StringBuilder(HTTP).append(ASK).
            append(CALLBACK_PARAM).append(EQV).append(CALLBACK_VALUE).append(AMP).
            append(MODULE_TYPE_PARAM).append(EQV).append(MODULE_TYPE_VALUE).append(AMP).
            append(ID_PARAM).append(EQV).append(ID_VALUE).append(AMP).
            append(VER_PARAM).append(EQV).append(VER_VALUE).append(AMP).
            append(TYPE_PARAM).append(EQV).append(TYPE_VALUE).append(AMP).
            append(THEME_PARAM).append(EQV).append(THEME_VALUE).append(AMP);
        return baseParamBuilder;
    }

    public static String createQuickSearchUrl(long countryParam, int adults, int children, int priceFrom, int priceTo,
                                       int pageNumber){
        StringBuilder quickSearchBuilder = new StringBuilder(getBaseParameters()).
        append(TOUR_KIND_PARAM).append(EQV).append(TOUR_KIND_VALUE).append(AMP).
        append(SWITCH_PRICE_PARAM).append(EQV).append(SWITCH_PRICE_VALUE).append(AMP).
        append(PREVIEW_PARAM).append(EQV).append(PREVIEW_VALUE).append(AMP).
        append(ITEMS_PER_PAGE_PARAM).append(EQV).append(ITEMS_PER_PAGE_VALUE).append(AMP).
        append(COUNTRY_PARAM).append(EQV).append(countryParam).append(AMP).
        append(HOTEL_RATING_PARAM).append(EQV).append(DEFAULT_HOTEL_RATING_VALUE).append(AMP).
        append(FOOD_PARAM).append(EQV).append(DEFAULT_FOOD_VALUE).append(AMP).
        append(ADULTS_PARAM).append(EQV).append(adults).append(AMP).
        append(CHILDREN_PARAM).append(EQV).append(children).append(AMP).
        append(DATE_FROM_PARAM).append(EQV).append(generateDate(new Date())).append(AMP).
        append(DATE_TILL_PARAM).append(EQV).append(generateDate(new Date(new Date().getTime() + TEN_DAYS_IN_MILLISECONDS))).append(AMP).
        append(NIGHTS_FROM_PARAM).append(EQV).append(DEFAULT_NIGHTS_FROM_VALUE).append(AMP).
        append(NIGHTS_TILL_PARAM).append(EQV).append(DEFAULT_NIGHTS_TILL_VALUE).append(AMP).
        append(PRICE_FROM_PARAM).append(EQV).append(priceFrom).append(AMP).
        append(PRICE_TILL_PARAM).append(EQV).append(priceTo).append(AMP).
        append(PAGE_NUMBER_PARAM).append(EQV).append(pageNumber).append(AMP).
        append(DEPARTURE_CITY_PARAM).append(EQV).append(DEFAULT_DEPARTURE_CITY_VALUE).append(AMP).
        append(ACTION_PARAM).append(EQV).append(ACTION_VALUE_PACKAGE).append(AMP).
        append(PACKAGE_TOUR_TYPE_PARAM).append(EQV).append(PACKAGE_TOUR_TYPE_VALUE);
        return quickSearchBuilder.toString();
    }

    public static String createAdvanceSearchUrl(long countryParam, long regionParam, Set<Integer> hotelStars, Set<String> food,
            int adults, int children, String dataFrom, String dataTill, int nightsFrom, int nightsTill, int priceFrom,
            int priceTo, int pageNumber){
        StringBuilder fullSearchBuilder = new StringBuilder(getBaseParameters()).
        append(TOUR_KIND_PARAM).append(EQV).append(TOUR_KIND_VALUE).append(AMP).
        append(SWITCH_PRICE_PARAM).append(EQV).append(SWITCH_PRICE_VALUE).append(AMP).
        append(PREVIEW_PARAM).append(EQV).append(PREVIEW_VALUE).append(AMP).
        append(ITEMS_PER_PAGE_PARAM).append(EQV).append(ITEMS_PER_PAGE_VALUE).append(AMP).
        append(COUNTRY_PARAM).append(EQV).append(countryParam).append(AMP).
        append(REGION_PARAM).append(EQV).append(regionParam).append(AMP).
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
        append(ACTION_PARAM).append(EQV).append(ACTION_VALUE_PACKAGE).append(AMP).
        append(PACKAGE_TOUR_TYPE_PARAM).append(EQV).append(PACKAGE_TOUR_TYPE_VALUE);
        return fullSearchBuilder.toString();
    }

    public static String createSearchUrlByHotel(Hotel hotel, int pageNumber){
        StringBuilder hotelSearchBuilder = new StringBuilder(getBaseParameters()).
        append(TOUR_KIND_PARAM).append(EQV).append(TOUR_KIND_VALUE).append(AMP).
        append(SWITCH_PRICE_PARAM).append(EQV).append(SWITCH_PRICE_VALUE).append(AMP).
        append(PREVIEW_PARAM).append(EQV).append(PREVIEW_VALUE).append(AMP).
        append(ITEMS_PER_PAGE_PARAM).append(EQV).append(ITEMS_PER_PAGE_VALUE).append(AMP).
        append(COUNTRY_PARAM).append(EQV).append(hotel.getRegion().getCountry().getItTourId()).append(AMP).
        append(REGION_PARAM).append(EQV).append(hotel.getRegion().getItTourId()).append(AMP).
        append(FOOD_PARAM).append(EQV).append(DEFAULT_FOOD_VALUE).append(AMP).
        append(ADULTS_PARAM).append(EQV).append(2).append(AMP).
        append(CHILDREN_PARAM).append(EQV).append(0).append(AMP).
        append(DATE_FROM_PARAM).append(EQV).append(generateDate(new Date())).append(AMP).
        append(DATE_TILL_PARAM).append(EQV).append(generateDate(new Date(new Date().getTime() + TEN_DAYS_IN_MILLISECONDS))).append(AMP).
        append(NIGHTS_FROM_PARAM).append(EQV).append(DEFAULT_NIGHTS_FROM_VALUE).append(AMP).
        append(NIGHTS_TILL_PARAM).append(EQV).append(DEFAULT_NIGHTS_TILL_VALUE).append(AMP).
        append(PRICE_FROM_PARAM).append(EQV).append(0).append(AMP).
        append(PRICE_TILL_PARAM).append(EQV).append(99000).append(AMP).
        append(PAGE_NUMBER_PARAM).append(EQV).append(pageNumber).append(AMP).
        append(DEPARTURE_CITY_PARAM).append(EQV).append(DEFAULT_DEPARTURE_CITY_VALUE).append(AMP).
        append(ACTION_PARAM).append(EQV).append(ACTION_VALUE_PACKAGE).append(AMP).
        append(PACKAGE_TOUR_TYPE_PARAM).append(EQV).append(PACKAGE_TOUR_TYPE_VALUE).append(AMP).
        append(HOTEL_PARAM).append(EQV).append(hotel.getItTourId());
        return hotelSearchBuilder.toString();
    }

    public static String createHotelInfoUrl(String[] id){
        StringBuilder stringBuilder = new StringBuilder(getBaseParameters()).
                append(ACTION_PARAM).append(EQV).append(ACTION_VALUE_FORM).append(AMP).
                append(TOUR_ID_PARAM).append(EQV).append(id[0]).append(AMP).
                append(SHARDING_RULE_ID_PARAM).append(EQV).append(id[1]);
        return stringBuilder.toString();
    }

    private static String hotelRating(Set<Integer> hotelStars){
        if(hotelStars == null || hotelStars.size() == 0 || hotelStars.size() == 4){
            return DEFAULT_HOTEL_RATING_VALUE;
        } else {
            StringBuilder hotelRatingBuilder = new StringBuilder();
            for(int stars : hotelStars){
                switch (stars){
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

    private static String foodValue(Set<String> food){
        if (food == null || food.size() == 0 || food.size() == 6){
            return DEFAULT_FOOD_VALUE;
        } else {
            StringBuilder foodBuilder = new StringBuilder();
            for(String foodSt : food){
                switch (foodSt){
                    case FOOD_HB: {
                        foodBuilder.append(FOOD_HB_CODE).append(PLUS);
                        break;
                    } case FOOD_BB: {
                        foodBuilder.append(FOOD_BB_CODE).append(PLUS);
                        break;
                    } case FOOD_FB: {
                        foodBuilder.append(FOOD_FB_CODE).append(PLUS);
                        break;
                    } case FOOD_AI: {
                        foodBuilder.append(FOOD_AI_CODE).append(PLUS);
                        break;
                    } case FOOD_UAI: {
                        foodBuilder.append(FOOD_UAI_CODE).append(PLUS);
                        break;
                    } case FOOD_RO: {
                        foodBuilder.append(FOOD_RO_CODE).append(PLUS);
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

    private static String generateDate(Date date){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR) - 2000;
        StringBuilder dateBuilder = new StringBuilder();
        if(day < 10){
            dateBuilder.append(0).append(day);
        } else {
            dateBuilder.append(day);
        }
        dateBuilder.append(".");
        if(month < 10){
            dateBuilder.append(0).append(month);
        } else {
            dateBuilder.append(month);
        }
        dateBuilder.append(".").append(year);
        return dateBuilder.toString();
    }

    public static void main(String[] args) {
        Hotel hotel = new Hotel("Adela Hotel", 3, new Region("Стамбул", new Country("Турция")));
        hotel.setItTourId(59466L);
        hotel.getRegion().setItTourId(5498L);
        hotel.getRegion().getCountry().setItTourId(318L);
        System.out.println(ItTourParserUrlGenerator.createSearchUrlByHotel(hotel, 1));
    }
}