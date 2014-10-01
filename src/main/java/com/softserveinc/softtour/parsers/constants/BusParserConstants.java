package com.softserveinc.softtour.parsers.constants;

public interface BusParserConstants {
    static final String RESOURCE_PATH_PARAMS = "/bus_parser_params";
    static final String DEFAULT_CHARSET = "UTF-8";
    static final String BUS_SELECT = "tr[class~=aslist(?)]";
    static final String QUESTION_MARK = "?";
    static final String AMPERSAND_MARK = "&";
    static final String EQUAL_MARK = "=";
    static final String URL = "http://bus.com.ua/cgi-bin/poshuk";
    static final String PARAM_CITY_FROM = "fp";
    static final String PARAM_CITY_TO = "tp";
    static final String PARAM_GO = "Go";
    static final int VALUE_GO = 3;
    static final String REDUNDANT_LINE = "Рейси з регулярністю від";
    static final String FIND_BY_TAG = "td";
    static final int MILLISECONDS_IN_DAY = 86400000;
    static final int MILLISECONDS_IN_HOUR = 3600000;
    static final int MILLISECONDS_IN_MINUTE = 60000;
}
