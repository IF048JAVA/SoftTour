package com.softserveinc.softtour.util;

import com.softserveinc.softtour.util.constants.BusParserUrlGeneratorConstants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    private static StringBuilder getBaseParameters(){
        StringBuilder baseParamBuilder = new StringBuilder(HTTP).append(ASK).
        append(DATE_ADD_PARAM).append(EQV).append(DATE_ADD_VALUE).append(AMP).
        append(FN_PARAM).append(EQV).append(FN_VALUE).append(AMP);
        return baseParamBuilder;
    }

    public String createSearchUrl(String cityFrom, String cityTo, String date){
        loadCityCodesProperties();
        StringBuilder searchBuilder = new StringBuilder(getBaseParameters()).
        append(POINT_FROM_PARAM).append(EQV).append(cityCodesProperties.getProperty(cityFrom)).append(AMP).
        append(POINT_TO_PARAM).append(EQV).append(cityCodesProperties.getProperty(cityTo)).append(AMP).
        append(DATE_PARAM).append(EQV).append(date);
        return searchBuilder.toString();
    }

    public static void main(String[] args) {
        BusParserUrlGenerator generator = new BusParserUrlGenerator();
        String url = generator.createSearchUrl("Київ", "Львів", "21.10.14");
        String doc = null;
        try {
            doc = Jsoup.connect(url).
                    timeout(20000).
                    ignoreContentType(true).
                    execute().
                    body();
        } catch (IOException e) {
            //TODO do something if there are no connection
            e.printStackTrace();
        }
        Document document = Jsoup.parse(doc);
        System.out.println(document);
    }
    //http://ticket.bus.com.ua/order/forming_bn
    // ?
    // point_from=UA8000000000
    // &
    // point_to=UA4610100000
    // &
    // date=20.10.14
    // &
    // date_add=0
    // &
    // fn=round_search
}
