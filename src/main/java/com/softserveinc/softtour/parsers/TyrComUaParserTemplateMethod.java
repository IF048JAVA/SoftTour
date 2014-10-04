package com.softserveinc.softtour.parsers;

import com.softserveinc.softtour.entity.Tour;
import com.softserveinc.softtour.parsers.constants.TyrComUaParserConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class TyrComUaParserTemplateMethod implements TyrComUaParserConstants {
    protected static WebDriver driver = new HtmlUnitDriver(true);
    protected static Properties countryUaRuVocabulary = new Properties();
    protected static Properties regionUaRuVocabulary = new Properties();
    protected static Properties departureCityUaRuVocabulary = new Properties();
    protected List<Tour> tourList = new ArrayList<>();
    protected String country;
    protected String region;
    protected String hotel;
    protected int[] stars;
    protected String[] foods;
    protected int adults;
    protected int children;
    protected int[] childrenAge;
    protected String dateFlyFrom;
    protected String dateFlyTo;
    protected int countNightsFrom;
    protected int countNightsTo;
    protected int priceFrom;
    protected int priceTo;
    protected String currency;
    protected String departureCity;

    static {
        try {
            InputStream inputCountry = TyrComUaParserTemplateMethod.class.getClass().
                    getResourceAsStream(RESOURCE_PATH_COUNTRY_VOCABULARY);
            InputStream inputRegion = TyrComUaParserTemplateMethod.class.getClass().
                    getResourceAsStream(RESOURCE_PATH_REGION_VOCABULARY);
            InputStream inputDepCity = TyrComUaParserTemplateMethod.class.getClass().
                    getResourceAsStream(RESOURCE_PATH_DEPARTURE_CITY_VOCABULARY);
            countryUaRuVocabulary.load(new InputStreamReader(inputCountry, DEFAULT_CHARSET));
            regionUaRuVocabulary.load(new InputStreamReader(inputRegion, DEFAULT_CHARSET));
            departureCityUaRuVocabulary.load(new InputStreamReader(inputDepCity, DEFAULT_CHARSET));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    protected TyrComUaParserTemplateMethod(String country, String region, String hotel, int[] stars, String[] foods,
            int adults, int children, int[] childrenAge, String dateFlyFrom, String dateFlyTo, int countNightsFrom,
            int countNightsTo, int priceFrom, int priceTo, String currency, String departureCity) {
        this.country = country;
        this.region = region;
        this.hotel = hotel;
        this.stars = stars;
        this.foods = foods;
        this.adults = adults;
        this.children = children;
        this.childrenAge = childrenAge;
        this.dateFlyFrom = dateFlyFrom;
        this.dateFlyTo = dateFlyTo;
        this.countNightsFrom = countNightsFrom;
        this.countNightsTo = countNightsTo;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.currency = currency;
        this.departureCity = departureCity;
        driver.get(URL_TYR_COM_UA);
    }

    public List<Tour> parse(){
        selectCountry(country);
        selectRegion(region);
        selectStars(stars);
        selectFood(foods);
        selectAdultsCount(adults);
        selectChildrenCount(children);
        selectChildrenAge(childrenAge);
        selectHotel(hotel);
        selectDateFlyFrom(dateFlyFrom);
        selectDateFlyTo(dateFlyTo);
        selectCountNightsFrom(countNightsFrom);
        selectCountNightsTo(countNightsTo);
        selectPriceFrom(priceFrom);
        selectPriceTo(priceTo);
        selectCurrency(currency);
        selectDepartureCity(departureCity);
        search();
        addAllWebElementsToWebElementList();
        return tourList;
    }

    public void quit(){
        driver.quit();
    }

    protected abstract void selectCountry(String country);
    protected abstract void selectRegion(String region);
    protected abstract void selectHotel(String hotel);
    protected abstract void selectStars(int...stars);
    protected abstract void selectFood(String...foods);
    protected abstract void selectAdultsCount(int adults);
    protected abstract void selectChildrenCount(int children);
    protected abstract void selectChildrenAge(int...childrenAge);
    protected abstract void selectDateFlyFrom(String dateFlyFrom);
    protected abstract void selectDateFlyTo(String dateFlyTo);
    protected abstract void selectCountNightsFrom(int countNightsFrom);
    protected abstract void selectCountNightsTo(int countNightsTo);
    protected abstract void selectPriceFrom(int priceFrom);
    protected abstract void selectPriceTo(int priceTo);
    protected abstract void selectCurrency(String currency);
    protected abstract void selectDepartureCity(String departureCity);
    protected abstract void search();
    protected abstract void addAllWebElementsToWebElementList();
}
