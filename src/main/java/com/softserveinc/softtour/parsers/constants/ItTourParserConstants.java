package com.softserveinc.softtour.parsers.constants;

import java.text.SimpleDateFormat;

public interface ItTourParserConstants {
    int CONNECTION_TIMEOUT = 20000;
    String UTF_8 = "UTF-8";
    String DEPARTURE_CITY_PROPERTIES_PATH = "/parser_properties/departure_city_ru-ua_vocabulary";

    String TAG_TR = "tr";
    String TAG_SPAN = "span";
    String TAG_DIV = "div";
    String TAG_A = "a";
    String TAG_TD = "td";
    String CLASS_ITT_TEXT_LEFT = "itt_text-left";
    String CLASS_TEXT_CENTER = "text-center";
    String CLASS_TEXT_RIGHT = "text-right";
    String CLASS_TR_FLIGHT_TO = "tr_flight_to";
    String ATTR_ONCLICK = "onclick";
    String ATTR_SRC = "src";
    String ID_IMG = "main_img_tour_in_view_open_";

    SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yy");
    SimpleDateFormat SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyHH:mm");
    String WITHOUT_FLY = "Без перельоту";
    String WRONG_APARTMENT_ROOM_TYPE = "APAR...";
    String WRONG_FAMILY_ROOM_TYPE = "FAMI...";
    String REGEXP_REPLACEMENT = "(return package_tour_order\\()|(\\);)";
    String NO_IMG = null;
    int DEFAULT_ADULTS_COUNT = 2;
    int DEFAULT_CHILDREN_COUNT = 0;

    int DATA_START_NUMBER = 7;
    int TAL_DATA_NUMBER = 2;
    int NOT_NEED_DATA_ELEMENT_NUMBER = 0;
    int TABLE_CELL_DEPARTURE_DATE = 1;
    int TABLE_CELL_DEPARTURE_TIME = 2;
    int TABLE_CELL_ROUTE = 7;
    int TABLE_CELL_ARRIVAL_TIME = 3;
    int TABLE_CELL_PRICE = 4;
    int TEN = 10;
    int LEADING_ZERO = 0;
}
