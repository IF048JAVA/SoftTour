package com.softserveinc.softtour.util;

import com.softserveinc.softtour.util.constants.StaticDataParserUrlGeneratorConstants;

public class StaticDataParserUrlGenerator implements StaticDataParserUrlGeneratorConstants {

    public String createCountryUrl(){
        StringBuilder countryUrlBuilder = new StringBuilder(COUNTRY_HTTP).append(ASK).
        append(ID_PARAM).append(EQV).append(ID_VALUE).append(AMP).
        append(VERSION_PARAM).append(EQV).append(VERSION_VALUE).append(AMP).
        append(TYPE_PARAM).append(EQV).append(TYPE_VALUE);
        return countryUrlBuilder.toString();
    }

    public String createRegionUrl(int countryCode){
        StringBuilder regionUrlBuilder = new StringBuilder(HTTP).append(ASK).
        append(ID_PARAM).append(EQV).append(ID_VALUE).append(AMP).
        append(VERSION_PARAM).append(EQV).append(VERSION_VALUE).append(AMP).
        append(TYPE_PARAM).append(EQV).append(TYPE_VALUE).append(AMP).
        append(CALLBACK_PARAM).append(EQV).append(CALLBACK_VALUE).append(AMP).
        append(MODULE_TYPE_PARAM).append(EQV).append(MODULE_TYPE_VALUE).append(AMP).
        append(THEME_PARAM).append(EQV).append(THEME_VALUE).append(AMP).
        append(ACTION_PARAM).append(EQV).append(ACTION_VALUE).append(AMP).
        append(EVENT_OWNER_LEVEL_PARAM).append(EQV).append(EVENT_OWNER_LEVEL_VALUE_ONE).append(AMP).
        append(COUNTRY_ID_PARAM).append(EQV).append(countryCode);
        return regionUrlBuilder.toString();
    }

    public String createHotelUrl(int countryCode, int regionCode){
        StringBuilder hotelUrlBuilder = new StringBuilder(createRegionUrl(countryCode)).append(AMP).
        append(HOTEL_RATING_ID_PARAM).append(EQV).append(DEFAULT_HOTEL_RATING_ID_VALUE).append(AMP).
        append(EVENT_OWNER_LEVEL_PARAM).append(EQV).append(EVENT_OWNER_LEVEL_VALUE_TWO).append(AMP).
        append(REGION_ID_PARAM).append(EQV).append(regionCode);
        return hotelUrlBuilder.toString();
    }
}
