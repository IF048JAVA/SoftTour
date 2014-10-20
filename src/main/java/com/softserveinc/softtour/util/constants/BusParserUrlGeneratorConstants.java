package com.softserveinc.softtour.util.constants;

public interface BusParserUrlGeneratorConstants {
    String UTF_8 = "UTF-8";
    String CITY_CODES_PROPERTIES_PATH = "/parser_properties/bus_parser_parameters";

    char ASK = '?';
    char EQV = '=';
    char AMP = '&';
    char PLUS = '+';

    String HTTP = "http://ticket.bus.com.ua/order/forming_bn";
    String DATE_ADD_PARAM = "date_add";
    int DATE_ADD_VALUE = 0;
    String FN_PARAM = "fn";
    String FN_VALUE = "round_search";

    String POINT_FROM_PARAM = "point_from";
    String POINT_TO_PARAM = "point_to";
    String DATE_PARAM = "date";
}
