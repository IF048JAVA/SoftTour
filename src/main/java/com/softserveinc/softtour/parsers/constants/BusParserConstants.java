package com.softserveinc.softtour.parsers.constants;

import java.text.SimpleDateFormat;

public interface BusParserConstants {
    int CONNECTION_TIMEOUT = 20000;

    String TAG_TR = "tr";
    String TAG_TD = "td";
    String TAG_B = "b";

    SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yy");
    SimpleDateFormat INPUT_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-ddHH:mm");
    SimpleDateFormat SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyHH:mm");
    long FIRST_DATE_REDUCE = 10800000;
    long SECOND_DATE_REDUCE = 97200000;
    long ONE_DAY_IN_MILLISECONDS = 86400000;
    int MINUTES_IN_HOUR = 60;

    char COLON = ':';
    String STAR = "*";

    int DATA_START_NUMBER = 7;
    int NOT_NEED_DATA_ELEMENT_NUMBER = 0;
    int TABLE_CELL_DEPARTURE_DATE = 1;
    int TABLE_CELL_DEPARTURE_TIME = 2;
    int TABLE_CELL_ROUTE = 7;
    int TABLE_CELL_ARRIVAL_TIME = 3;
    int TABLE_CELL_PRICE = 4;
    int TEN = 10;
    int LEADING_ZERO = 0;
}
