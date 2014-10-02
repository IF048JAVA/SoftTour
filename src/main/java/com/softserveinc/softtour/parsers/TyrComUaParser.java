package com.softserveinc.softtour.parsers;

import com.softserveinc.softtour.entity.*;
import com.softserveinc.softtour.parsers.constants.TyrComUaParserConstants;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.NoSuchElementException;

public class TyrComUaParser implements TyrComUaParserConstants {
    private static WebDriver driver = new HtmlUnitDriver(true);
    private static Properties countryUaRuVocabulary = new Properties();
    private static Properties regionUaRuVocabulary = new Properties();
    private static Properties departureCityUaRuVocabulary = new Properties();
    private List<Tour> tourList = new ArrayList<>();
    private String country;
    private String region;
    private String hotel;
    private int[] stars;
    private String[] foods;
    private int adults;
    private int children;
    private int[] childrenAge;
    private String dateFlyFrom;
    private String dateFlyTo;
    private int countNightsFrom;
    private int countNightsTo;
    private int priceFrom;
    private int priceTo;
    private String currency;
    private String departureCity;

    static {
        try {
            InputStream inputCountry = TyrComUaParser.class.getClass().
                                       getResourceAsStream(RESOURCE_PATH_COUNTRY_VOCABULARY);
            InputStream inputRegion = TyrComUaParser.class.getClass().
                                      getResourceAsStream(RESOURCE_PATH_REGION_VOCABULARY);
            InputStream inputDepCity = TyrComUaParser.class.getClass().
                                       getResourceAsStream(RESOURCE_PATH_DEPARTURE_CITY_VOCABULARY);
            countryUaRuVocabulary.load(new InputStreamReader(inputCountry, DEFAULT_CHARSET));
            regionUaRuVocabulary.load(new InputStreamReader(inputRegion, DEFAULT_CHARSET));
            departureCityUaRuVocabulary.load(new InputStreamReader(inputDepCity, DEFAULT_CHARSET));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public TyrComUaParser(String country, String region, String hotel, int[] stars, String[] foods, int adults,
                          int children, int[] childrenAge, String dateFlyFrom, String dateFlyTo, int countNightsFrom,
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
        selectHotel(hotel);
        selectStars(stars);
        selectFood(foods);
        selectAdultsCount(adults);
        selectChildrenCount(children);
        selectChildrenAge(childrenAge);
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

    private void selectCountry(String country){
        if(country.equalsIgnoreCase(DEFAULT_COUNTRY)){
            return;
        } else {
            Select dropDownCountry = new Select(driver.findElement(By.id(DROP_DOWN_COUNTRY_ID)));
            String countryRu = countryUaRuVocabulary.getProperty(country);
            dropDownCountry.selectByVisibleText(countryRu);
        }
    }

    private void selectRegion(String region){
        //TODO can be default region
        if(region == null){
            return;
        } else {
            WebElement selectRegion = driver.findElement(By.id(SELECT_REGION_ID));
            Select dropDownRegion = new Select(selectRegion);
            String regionRu = regionUaRuVocabulary.getProperty(region);
            dropDownRegion.selectByVisibleText(regionRu);
        }
    }

    private void selectHotel(String hotel){
        //TODO can be default hotel
        if(hotel == null){
            return;
        } else {
            WebElement selectHotel = driver.findElement(By.id(SELECT_HOTEL_ID));
            Select dropHotel = new Select(selectHotel);
            dropHotel.selectByVisibleText(hotel);
        }
    }

    private void selectStars(int...stars){
        //TODO How to select stars? Almost all selected, 2 discarded by default
        if(stars.length == 0){
            return;
        }
        boolean[] starsBoolean = new boolean[4];
        for(int star : stars){
            switch (star){
                case 2:{
                    starsBoolean[0] = true;
                    break;
                }case 3:{
                    starsBoolean[1] = true;
                    break;
                }case 4:{
                    starsBoolean[2] = true;
                    break;
                }case 5:{
                    starsBoolean[3] = true;
                    break;
                }default:{
                    throw new NoSuchElementException("There are no hotels with " + star + " stars");
                }
            }
        }
        if (starsBoolean[0]){
            WebElement multiplyStars2 = driver.findElement(By.xpath(MULTIPLY_STARS_2_XPATH));
            multiplyStars2.click();
        }
        if (!starsBoolean[1]){
            WebElement multiplyStars3 = driver.findElement(By.xpath(MULTIPLY_STARS_3_XPATH));
            multiplyStars3.clear();
        }
        if (!starsBoolean[2]){
            WebElement multiplyStars4 = driver.findElement(By.xpath(MULTIPLY_STARS_4_XPATH));
            multiplyStars4.clear();
        }
        if (!starsBoolean[3]){
            WebElement multiplyStars5 = driver.findElement(By.xpath(MULTIPLY_STARS_5_XPATH));
            multiplyStars5.clear();
        }
    }

    private void selectFood(String...foods){
        if(foods.length == 0){
            return;
        }
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(foods));
        if(!list.contains(FOOD_HB_XPATH)){
            WebElement multiplyFoodHB = driver.findElement(By.xpath(MULTIPLY_FOOD_HB_XPATH));
            multiplyFoodHB.clear();
        }
        if(!list.contains(FOOD_BB_XPATH)){
            WebElement multiplyFoodBB = driver.findElement(By.xpath(MULTIPLY_FOOD_BB_XPATH));
            multiplyFoodBB.clear();
        }
        if(!list.contains(FOOD_FB_XPATH)){
            WebElement multiplyFoodFB = driver.findElement(By.xpath(MULTIPLY_FOOD_FB_XPATH));
            multiplyFoodFB.clear();
        }
        if(!list.contains(FOOD_AI_XPATH)){
            WebElement multiplyFoodAI = driver.findElement(By.xpath(MULTIPLY_FOOD_AI_XPATH));
            multiplyFoodAI.clear();
        }
        if(!list.contains(FOOD_UAI_XPATH)){
            WebElement multiplyFoodUAI = driver.findElement(By.xpath(MULTIPLY_FOOD_UAI_XPATH));
            multiplyFoodUAI.clear();
        }
        if(!list.contains(FOOD_RO_XPATH)){
            WebElement multiplyFoodRO = driver.findElement(By.xpath(MULTIPLY_FOOD_RO_XPATH));
            multiplyFoodRO.clear();
        }
    }

    private void selectAdultsCount(int adults){
        if(adults==2){
            return;
        } else if(adults > 5 || adults < 1){
            throw new NoSuchElementException("There are more then 5 adults or less then 1: " + adults);
        } else {
            WebElement selectAdultsCount = driver.findElement(By.id(DROP_DOWN_ADULTS_ID));
            Select dropDownAdults = new Select(selectAdultsCount);
            dropDownAdults.selectByVisibleText(String.valueOf(adults));
        }
    }

    private void selectChildrenCount(int children){
        if(children==0){
            return;
        } else if (children > 3 || children < 0){
            throw new NoSuchElementException("There are more then 3 children or less then 0: " + children);
        } else {
            WebElement selectChildrenCount = driver.findElement(By.id(DROP_DOWN_CHILDREN_ID));
            Select dropChildren = new Select(selectChildrenCount);
            dropChildren.selectByVisibleText(String.valueOf(children));
        }
    }

    private void selectChildrenAge(int...childrenAge){
        if(childrenAge.length == 0){
            return;
        }
        switch (childrenAge.length-1){
            case 0:{
                WebElement childrenAge1 = driver.findElement(By.id(SEND_CHILD_1_AGE_ID));
                childrenAge1.sendKeys(String.valueOf(childrenAge[0]));
                break;
            }case 1:{
                WebElement childrenAge2 = driver.findElement(By.id(SEND_CHILD_2_AGE_ID));
                childrenAge2.sendKeys(String.valueOf(childrenAge[1]));
                break;
            }case 2:{
                WebElement childrenAge3 = driver.findElement(By.id(SEND_CHILD_3_AGE_ID));
                childrenAge3.sendKeys(String.valueOf(childrenAge[2]));
                break;
            }default:{
                throw new NoSuchElementException("There are must be count of children less then 3 ");
            }
        }
    }

    private void selectDateFlyFrom(String dateFlyFrom){
        //TODO What date is default in our app?
        if(dateFlyFrom == null){
            return;
        } else {
            WebElement dataFlyFrom = driver.findElement(By.id(SEND_DATE_FLY_FROM_ID));
            dataFlyFrom.clear();
            dataFlyFrom.sendKeys(dateFlyFrom);
        }
    }

    private void selectDateFlyTo(String dateFlyTo){
        //TODO What date is default in our app?
        if(dateFlyTo == null){
            return;
        } else {
            WebElement dataFlyTo = driver.findElement(By.id(SEND_DATE_FLY_TO_ID));
            dataFlyTo.clear();
            dataFlyTo.sendKeys(dateFlyTo);
        }
    }

    private void selectCountNightsFrom(int countNightsFrom){
        //TODO Is count nights from 1 to 21 in our app?
        if (countNightsFrom == 6 || (countNightsFrom <= 0 || countNightsFrom > 21)){
            return;
        } else {
            WebElement nightsFrom = driver.findElement(By.id(DROP_DOWN_NIGHTS_FROM_ID));
            Select dropDownNightsFrom = new Select(nightsFrom);
            dropDownNightsFrom.selectByVisibleText(String.valueOf(countNightsFrom));
        }
    }

    private void selectCountNightsTo(int countNightsTo){
        //TODO Is count nights from 1 to 21 in our app?
        if (countNightsTo == 14 || (countNightsTo <=0 || countNightsTo > 21)){
            return;
        } else {
            WebElement nightsTo = driver.findElement(By.id(DROP_DOWN_NIGHTS_TO_ID));
            Select dropDownNightsTo = new Select(nightsTo);
            dropDownNightsTo.selectByVisibleText(String.valueOf(countNightsTo));
        }
    }

    private void selectPriceFrom(int priceFrom){
        if (priceFrom == 0){
            return;
        } else {
            WebElement price = driver.findElement(By.id(SEND_PRICE_FROM_ID));
            price.clear();
            price.sendKeys(String.valueOf(priceFrom));
        }
    }

    private void selectPriceTo(int priceTo){
        if (priceTo == 99000){
            return;
        } else {
            WebElement price = driver.findElement(By.id(SEND_PRICE_TO_ID));
            price.clear();
            price.sendKeys(String.valueOf(priceTo));
        }
    }

    private void selectCurrency(String currency){
        if (currency.equalsIgnoreCase(DEFAULT_CURRENCY)){
            return;
        } else {
            WebElement selectCurrency = driver.findElement(By.id(DROP_DOWN_CURRENCY_ID));
            Select dropDownCurrency = new Select(selectCurrency);
            dropDownCurrency.selectByVisibleText(currency);
        }
    }

    private void selectDepartureCity(String departureCity){
        if(departureCity.equals(DEFAULT_DEPARTURE_CITY)){
            return;
        } else {
            WebElement departure = driver.findElement(By.name(DROP_DOWN_DEPARTURE_CITY_NAME));
            Select dropDownDeparture = new Select(departure);
            String departureCityRu = departureCityUaRuVocabulary.getProperty(departureCity);
            dropDownDeparture.selectByVisibleText(departureCityRu);
        }
    }

    private void search(){
        WebElement radioButtonCountOfShowPages = driver.findElement(By.id(RADIO_BUTTON_COUNT_PAGES_100_ID));
        radioButtonCountOfShowPages.click();
        WebElement buttonSubmit = driver.findElement(By.xpath(BUTTON_SUBMIT_XPATH));
        buttonSubmit.click();
    }

    public void quit(){
        driver.quit();
    }

    public void addAllWebElementsToWebElementList(){
        for(int i = 2; i<COUNT_RESULT_PAGES;i++){
            ArrayList<WebElement> oddList = (ArrayList<WebElement>)
                    driver.findElements(By.className(PARSE_RESULTS_BY_CLASS_NAME_ODD));
            ArrayList<WebElement> evenList = (ArrayList<WebElement>)
                    driver.findElements(By.className(PARSE_RESULTS_BY_CLASS_NAME_EVEN));
            //I do it to save same order of elements, as in search result
            List<WebElement> webElementList2 = new ArrayList<>();
            for(int j = 0; j<evenList.size(); j++){
                webElementList2.add(oddList.get(j));
                webElementList2.add(evenList.get(j));
            }
            if(oddList.size()>evenList.size()){
                webElementList2.add(oddList.get(oddList.size()-1));
            }
            for(WebElement webElement : webElementList2){
                addTourToList(webElement);
            }
            oddList.clear();
            evenList.clear();
            WebElement nextPage = null;
            try{
                nextPage = driver.findElement(By.linkText(String.valueOf(i)));
                nextPage.click();
            } catch (org.openqa.selenium.NoSuchElementException e){
                return;
            }
        }
    }

    public void addTourToList(WebElement webElement){
        List<String> tourDataList = new ArrayList<>();
        List<WebElement> listLeft = webElement.findElements(By.className(RESULT_LIST_LEFT_CLASS_NAME));
        List<WebElement> listCenter = webElement.findElements(By.className(RESULT_LIST_CENTER_CLASS_NAME));
        WebElement textRight = webElement.findElement(By.className(RESULT_LIST_RIGHT_CLASS_NAME));

        for(int i = 0; i<listLeft.size();i++){
            tourDataList.add(listLeft.get(i).getText());
        }
        listLeft.clear();
        for(int i = 0; i<listCenter.size();i++){
            tourDataList.add(listCenter.get(i).getText());
        }
        listCenter.clear();
        tourDataList.add(textRight.getText());

        /*
        list elements
      0  region
      1  Hotel name
      2  ? Std
      3  departure city
      4  stars
      5  food
      6  tour days
      7  departure date
      8  link
      9  price
         */

        Tour tour = new Tour();

        //set tour date
        //Поки-що дата туру буде датою вильоту :(
        String tourDate = tourDataList.get(7);
        SimpleDateFormat dateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT_DATE);
        Date date = null;
        java.sql.Date sqlDate = null;
        try {
            date = dateFormat.parse(tourDate);
            sqlDate = new java.sql.Date(date.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        tour.setDate(sqlDate);

        //set tour days
        int days = Integer.parseInt(tourDataList.get(6));
        tour.setDays(days);

        //set departure city
        String depCity = tourDataList.get(3);
        if(depCity.equalsIgnoreCase(NO_DEPARTURE)) {
            tour.setDepartureCity(NO_DEPARTURE_UA);
        } else {
            tour.setDepartureCity(depCity);
        }

        //set departure time
        String depTime = tourDataList.get(7);
        SimpleDateFormat depDateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT_DATE);
        Date departureTime = null;
        java.sql.Date sqlDateDepart = null;
        try {
            departureTime = depDateFormat.parse(depTime);
            sqlDateDepart = new java.sql.Date(departureTime.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tour.setDepartureTime(sqlDateDepart);

        String priceSt = tourDataList.get(9);
        char [] priceCh = priceSt.toCharArray();
        StringBuffer priceOnlyNumbers = new StringBuffer();
        for(char ch : priceCh){
            if(ch >= 48 && ch <= 57){
                priceOnlyNumbers.append(ch);
            }
        }
        int priceInt = Integer.parseInt(priceOnlyNumbers.toString());
        BigDecimal price = new BigDecimal(priceInt);
        tour.setPrice(price);

        //set Hotel
        setHotel(tour, tourDataList);

        //set Food
        String foodSt = tourDataList.get(5);
        Food food = new Food(foodSt);
        tour.setFood(food);

        //add tour
        tourList.add(tour);
    }

    private void setHotel(Tour tour, List<String> tourDataList){
        Country coun = new Country(country);
        Region reg = new Region(region, coun);
        int star = Integer.parseInt(tourDataList.get(4));
        String hotelName = tourDataList.get(1);
        Hotel hot = new Hotel(hotelName, star, reg);
        // it makes parser faster
        int size = tourList.size();
        if (size > 0) {
            Hotel prevHot = tourList.get(size-1).getHotel();
            String prevHotelName = prevHot.getName();
            if (prevHotelName.equals(hotelName) || hotel != null){
                hot.setImgUrl(prevHot.getImgUrl());
                tour.setHotel(hot);
                return;
            }
        }

        WebElement linkToPicture = driver.findElement(By.xpath(LINK_TO_PICTURE));
        linkToPicture.click();
        String st = "";
        try {
            st = (String) ((JavascriptExecutor) driver).executeScript(JAVASCRIPT_CODE);
        } catch (WebDriverException e){
            st = NO_PICTURE;
        } finally {
            hot.setImgUrl(st);
            tour.setHotel(hot);
        }
    }

    public static void main(String[] args) {
        int[] stars = {2, 3, 4, 5};
        String[] foods = {"HB", "AI"};
        int[] childrenAge = {};
        TyrComUaParser parser = new TyrComUaParser("Туреччина", null, null, stars, foods, 3, 0, childrenAge,
                "01.10.14", "31.12.14", 6, 21, 6000, 120000, "Грн", "Київ");
        List<Tour> resultList = parser.parse();
        for(int i = 0; i<resultList.size(); i++){
            System.out.println(resultList.get(i).toString());
        }
        //don't forget to use this method
        parser.quit();
    }
}

