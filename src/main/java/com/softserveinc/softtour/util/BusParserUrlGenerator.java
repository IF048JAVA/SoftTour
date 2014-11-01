package com.softserveinc.softtour.util;

import com.softserveinc.softtour.util.constants.BusParserUrlGeneratorConstants;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

public class BusParserUrlGenerator implements BusParserUrlGeneratorConstants {
    private Properties cityCodesProperties = new Properties();

    private void loadCityCodesProperties(){
        try {
            InputStream inputCountryProperties = this.getClass().
                    getResourceAsStream(CITY_CODES_PROPERTIES_PATH);
            cityCodesProperties.load(new InputStreamReader(inputCountryProperties, UTF_8));
        }catch (IOException e){
            //TODO improve handled exception
            System.out.println(e.getMessage());
        }
    }

    private StringBuilder getBaseParameters(){
        StringBuilder baseParamBuilder = new StringBuilder(HTTP).append(ASK).
        append(DATE_ADD_PARAM).append(EQV).append(DATE_ADD_VALUE).append(AMP).
        append(FN_PARAM).append(EQV).append(FN_VALUE).append(AMP);
        return baseParamBuilder;
    }

    public String[] createSearchUrl(String cityFrom, String cityTo, java.util.Date date){
        String[] urlArray = new String[2];
        loadCityCodesProperties();
        StringBuilder searchBuilder = new StringBuilder(getBaseParameters()).
        append(POINT_FROM_PARAM).append(EQV).append(cityCodesProperties.getProperty(cityFrom)).append(AMP).
        append(POINT_TO_PARAM).append(EQV).append(cityCodesProperties.getProperty(cityTo)).append(AMP);
        String searchResult = searchBuilder.toString();
        String dateReduce = generateDate(date, FIRST_DATE_REDUCE);
        urlArray[0] = new StringBuilder(searchResult).append(DATE_PARAM).append(EQV).append(dateReduce).toString();
        dateReduce = generateDate(date, SECOND_DATE_REDUCE);
        urlArray[1] = new StringBuilder(searchResult).append(DATE_PARAM).append(EQV).append(dateReduce).toString();
        return urlArray;
    }

    public String createButtonUrl(String cityFrom, String cityTo, String date){
        loadCityCodesProperties();
        Date utilDate = null;
        try {
            utilDate = INPUT_DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            utilDate = new Date();
            e.printStackTrace();
        }
        StringBuilder searchBuilder = new StringBuilder(getBaseParameters()).
                append(POINT_FROM_PARAM).append(EQV).append(cityCodesProperties.getProperty(cityFrom)).append(AMP).
                append(POINT_TO_PARAM).append(EQV).append(cityCodesProperties.getProperty(cityTo)).append(AMP).
                append(DATE_PARAM).append(EQV).append(SIMPLE_DATE_FORMAT.format(utilDate));
        return searchBuilder.toString();
    }
    /**
     * @param dateReduce - milliseconds of date reducing
     * @return web-page's url with bus routes
     */
    private String generateDate(java.util.Date date, long dateReduce){
        java.util.Date reducedDate = new java.util.Date(date.getTime() - dateReduce);
        String dateString = SIMPLE_DATE_FORMAT.format(reducedDate);
        return dateString;
    }

    public static void main(String[] args) throws ParseException{
        BusParserUrlGenerator generator = new BusParserUrlGenerator();
        String url = generator.createButtonUrl("Київ", "Львів", "2014-11-23");
    }
}
