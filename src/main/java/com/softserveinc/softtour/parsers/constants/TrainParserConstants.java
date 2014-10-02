package com.softserveinc.softtour.parsers.constants;

public interface TrainParserConstants {
    static final String RESOURCE_PATH_PARAMS = "/parser_properties/train_parser_params";
    static final String DEFAULT_CHARSET = "UTF-8";
    static final String TRAIN_SELECT = "td";
    static final String ZERO_MARK = "0";
    static final String POINT_MARK = ".";
    static final String QUESTION_MARK = "?";
    static final String AMPERSAND_MARK = "&";
    static final String EQUAL_MARK = "=";
    static final String URL_UZ_GOV_UA = "http://www.uz.gov.ua/passengers/timetables_cis/";
    static final String PARAM_CITY_FROM = "from_station";
    static final String PARAM_CITY_TO = "to_station";
    static final String PARAM_DATE = "start_date";
    static final String PARAM_TIME = "select_time";
    static final int VALUE_TIME = 2;
    static final String PARAM_TIME_FROM = "time_from";
    static final String VALUE_TIME_FROM = "00";
    static final String PARAM_TIME_TO = "time_to";
    static final int VALUE_TIME_TO= 24;
    static final String PARAM_SEARCH = "by_route";
    static final String VALUE_SEARCH = "Пошук";
    static final int MILLISECONDS_IN_DAY = 86400000;
    static final int MILLISECONDS_IN_HOUR = 3600000;
    static final int MILLISECONDS_IN_MINUTE = 60000;
    static final int FOR_CYCLE_STEP = 10;
}
