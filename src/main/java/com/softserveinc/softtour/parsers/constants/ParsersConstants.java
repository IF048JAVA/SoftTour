package com.softserveinc.softtour.parsers.constants;

public interface ParsersConstants {
    int CONNECTION_TIMEOUT = 20000;
    String UTF_8 = "UTF-8";
    String DEPARTURE_CITY_PROPERTIES_PATH = "/parser_properties/departure_city_ru-ua_vocabulary";

    String TAG_TR = "tr";
    String TAG_SPAN = "span";
    String TAG_DIV = "div";
    String TAG_A = "a";
    String TAG_TD = "td";
    String TAG_B = "b";
    String TAG_OPTION = "option";
    String CLASS_ITT_TEXT_LEFT = "itt_text-left";
    String CLASS_TEXT_CENTER = "text-center";
    String CLASS_TEXT_RIGHT = "text-right";
    String CLASS_TR_FLIGHT_TO = "tr_flight_to";
    String ATTR_ONCLICK = "onclick";
    String ATTR_SRC = "src";
    String ATTR_VALUE = "value";
    String ID_IMG = "main_img_tour_in_view_open_";
    String ID_COUNTRY = "itt_country";

    String DAY_FORMAT = "dd.MM.yy";
    String BUS_DAY_FORMAT = "yyyy-MM-dd.HH:mm";
    int THREE_HOURS_IN_MILLISECONDS = 10800000;
    int TWENTY_SEVEN_HOURS_IN_MILLISECONDS = 97200000;
    String TIME_FORMAT = "dd.MM.yy.HH:mm";
    String WITHOUT_FLY = "Без перельоту";
    String WRONG_APARTMENT_ROOM_TYPE = "APAR...";
    String WRONG_FAMILY_ROOM_TYPE = "FAMI...";
    String REGEXP_REPLACEMENT = "(return package_tour_order\\()|(\\);)";
    String NO_IMG = null;
    String ALL_HOTELS = "Все отели";
    String ALL_CITIES = "Все города";
}
