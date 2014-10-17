package com.softserveinc.softtour.parsers.constants;

public interface ItTourParserConstants {
    int CONNECTION_TIMEOUT = 20000;

    String TAG_TR = "tr";
    String TAG_SPAN = "span";
    String TAG_DIV = "div";
    String TAG_A = "a";
    String CLASS_ITT_TEXT_LEFT = "itt_text-left";
    String CLASS_TEXT_CENTER = "text-center";
    String CLASS_TEXT_RIGHT = "text-right";
    String ATTR_ONCLICK = "onclick";
    String ATTR_SRC = "src";
    String ID_IMG = "main_img_tour_in_view_open_";

    String DAY_FORMAT = "dd.mm.yy";
    String WITHOUT_FLY = "Без перельоту";
    String WRONG_APARTMENT_ROOM_TYPE = "APAR...";
    String WRONG_FAMILY_ROOM_TYPE = "FAMI...";
    String REGEXP_REPLACEMENT = "(return package_tour_order\\()|(\\);)";
    String NO_IMG = "NO IMG";
}
