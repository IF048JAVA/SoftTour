package com.softserveinc.softtour.parsers;

import com.softserveinc.softtour.entity.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by oleksandrgasenuk on 20.09.14.
 */

public class TyrComUaParser {
    private static final String URL = "http://www.tyr.com.ua/tours/search.php";
    private static Map<String, String> countryUaRuVocabulary = new HashMap<>();
    private static Map<String, String> regionUaRuVocabulary = new HashMap<>();
    private static Map<String, String> departureCityUaRuVocabulary = new HashMap<>();
    private WebDriver driver = new HtmlUnitDriver(true);
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
        //Єгипет
        countryUaRuVocabulary.put("Єгипет", "Египет");
        regionUaRuVocabulary.put("Дахаб", "Дахаб");
        regionUaRuVocabulary.put("Макаді Бей", "Макади Бей");
        regionUaRuVocabulary.put("Марса Алам", "Марса Алам");

        //Туреччина
        countryUaRuVocabulary.put("Туреччина", "Турция");
        regionUaRuVocabulary.put("Аланья", "Аланья");
        regionUaRuVocabulary.put("Анталія", "Анталия");
        regionUaRuVocabulary.put("Белек", "Белек");

        //Греція
        countryUaRuVocabulary.put("Греція", "Греция");
        regionUaRuVocabulary.put("Агія Тріада", "Агия Триада");
        regionUaRuVocabulary.put("Астіпалея", "Астипалея");
        regionUaRuVocabulary.put("Аттика", "Аттика");

        departureCityUaRuVocabulary.put("Київ", "Киев");
        departureCityUaRuVocabulary.put("Львів", "Львов");
        departureCityUaRuVocabulary.put("Харків", "Харьков");
        departureCityUaRuVocabulary.put("Одеса", "Одесса");
        departureCityUaRuVocabulary.put("Запоріжжя", "Запорожье");
        departureCityUaRuVocabulary.put("Дніпропетровськ", "Днепропетровск");
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
        driver.get(URL);
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

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    private void selectCountry(String country){
        if(country.equalsIgnoreCase("Єгипет")){
            return;
        }
        Select dropCountry = new Select(driver.findElement(By.id("itt_country")));
        String countryRu = countryUaRuVocabulary.get(country);
        dropCountry.selectByVisibleText(countryRu);
    }

    private void selectRegion(String region){
        if(region == null){
            return;
        }
        WebElement selectRegion = driver.findElement(By.id("region_list"));
        Select dropRegion = new Select(selectRegion);
        String regionRu = regionUaRuVocabulary.get(region);
        dropRegion.selectByVisibleText(regionRu);
    }

    private void selectHotel(String hotel){
        if(hotel == null){
            return;
        }
        WebElement selectHotel = driver.findElement(By.id("hotel_list"));
        Select dropHotel = new Select(selectHotel);
        dropHotel.selectByVisibleText(hotel);
    }

    private void selectStars(int...stars){
        if(stars.length == 0){
            return;
        }
        //almost all selected, 2 discarded
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
            WebElement multiplyButtonStars2 = driver.findElement(By.xpath("//input[@value='7' and @name='hotel_rating_list']"));
            multiplyButtonStars2.click();
        }
        if (!starsBoolean[1]){
            WebElement multiplyButtonStars3 = driver.findElement(By.xpath("//input[@value='3' and @name='hotel_rating_list']"));
            multiplyButtonStars3.clear();
        }
        if (!starsBoolean[2]){
            WebElement multiplyButtonStars4 = driver.findElement(By.xpath("//input[@value='4' and @name='hotel_rating_list']"));
            multiplyButtonStars4.clear();
        }
        if (!starsBoolean[3]){
            WebElement multiplyButtonStars5 = driver.findElement(By.xpath("//input[@value='78' and @name='hotel_rating_list']"));
            multiplyButtonStars5.clear();
        }
    }



    private void selectFood(String...foods){
        if(foods.length == 0){
            return;
        }
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(foods));
        if(!list.contains("HB")){
            WebElement radioButtonFoodHB = driver.findElement(By.xpath("//input[@value='496' and @name='food_list']"));
            radioButtonFoodHB.clear();
        }
        if(!list.contains("BB")){
            WebElement radioButtonFoodBB = driver.findElement(By.xpath("//input[@value='388' and @name='food_list']"));
            radioButtonFoodBB.clear();
        }
        if(!list.contains("FB")){
            WebElement radioButtonFoodFB = driver.findElement(By.xpath("//input[@value='498' and @name='food_list']"));
            radioButtonFoodFB.clear();
        }
        if(!list.contains("AI")){
            WebElement radioButtonFoodAI = driver.findElement(By.xpath("//input[@value='512' and @name='food_list']"));
            radioButtonFoodAI.clear();
        }
        if(!list.contains("UAI")){
            WebElement radioButtonFoodUAI = driver.findElement(By.xpath("//input[@value='560' and @name='food_list']"));
            radioButtonFoodUAI.clear();
        }
        if(!list.contains("RO")){
            WebElement radioButtonFoodRO = driver.findElement(By.xpath("//input[@value='1956' and @name='food_list']"));
            radioButtonFoodRO.clear();
        }
    }

    private void selectAdultsCount(int adults){
        if(adults==2){
            return;
        } else if(adults > 5 || adults < 1){
            throw new NoSuchElementException("There are more then 5 adults or less then 1: " + adults);
        }
        WebElement selectAdultsCount = driver.findElement(By.id("adult"));
        Select dropAdults = new Select(selectAdultsCount);
        dropAdults.selectByVisibleText(String.valueOf(adults));
    }

    private void selectChildrenCount(int children){
        if(children==0){
            return;
        } else if (children > 3 || children < 0){
            throw new NoSuchElementException("There are more then 3 children or less then 0: " + children);
        }
        WebElement selectChildrenCount = driver.findElement(By.id("children"));
        Select dropChildren = new Select(selectChildrenCount);
        dropChildren.selectByVisibleText(String.valueOf(children));

    }

    private void selectChildrenAge(int...childrenAge){
        if(childrenAge.length == 0){
            return;
        }
        switch (childrenAge.length-1){
            case 0:{
                WebElement childrenAge1 = driver.findElement(By.id("child1_age"));
                childrenAge1.sendKeys(String.valueOf(childrenAge[0]));
                break;
            }case 1:{
                WebElement childrenAge2 = driver.findElement(By.id("child2_age"));
                childrenAge2.sendKeys(String.valueOf(childrenAge[1]));
                break;
            }case 2:{
                WebElement childrenAge3 = driver.findElement(By.id("child3_age"));
                childrenAge3.sendKeys(String.valueOf(childrenAge[2]));
                break;
            }default:{
                throw new NoSuchElementException("Wow, you are cool parent, but there are no place in form for " +
                                                 childrenAge.length + " children. Sorry.");
            }
        }
    }

    private void selectDateFlyFrom(String dateFlyFrom){
        if(dateFlyFrom == null){
            return;
        }
        WebElement dataFlyFrom = driver.findElement(By.id("itt_date_from"));
        dataFlyFrom.clear();
        dataFlyFrom.sendKeys(dateFlyFrom);
    }

    private void selectDateFlyTo(String dateFlyTo){
        if(dateFlyTo == null){
            return;
        }
        WebElement dataFlyTo = driver.findElement(By.id("date_till"));
        dataFlyTo.clear();
        dataFlyTo.sendKeys(dateFlyTo);
    }

    private void selectCountNightsFrom(int countNightsFrom){
        if(countNightsFrom == 6 || (countNightsFrom <= 0 || countNightsFrom > 21)){
            return;
        }
        WebElement nightsFrom = driver.findElement(By.id("night_from"));
        Select dropNightsFrom = new Select(nightsFrom);
        dropNightsFrom.selectByVisibleText(String.valueOf(countNightsFrom));
    }

    private void selectCountNightsTo(int countNightsTo){
        if(countNightsTo == 14 || (countNightsTo <=0 || countNightsTo > 21)){
            return;
        }
        WebElement nightsTo = driver.findElement(By.id("night_till"));
        Select dropNightsTo = new Select(nightsTo);
        dropNightsTo.selectByVisibleText(String.valueOf(countNightsTo));
    }

    private void selectPriceFrom(int priceFrom){
        if(priceFrom == 0){
            return;
        } else if(priceFrom > 99999){
            priceFrom = 99999;
        } else if(priceFrom < 0){
            priceFrom *= -1;
        }
        WebElement price = driver.findElement(By.id("price_from"));
        price.clear();
        price.sendKeys(String.valueOf(priceFrom));
    }

    private void selectPriceTo(int priceTo){
        if(priceTo == 99000 || priceTo == 0){
            return;
        } else if(priceTo > 99999){
            priceTo = 99999;
        } else if(priceTo < 0){
            priceTo *= -1;
        }
        WebElement price = driver.findElement(By.id("price_till"));
        price.clear();
        price.sendKeys(String.valueOf(priceTo));
    }

    private void selectCurrency(String currency){
        switch (currency){
            case "USD":{
                break;
            }case "Грн":{
                WebElement selectCurrency = driver.findElement(By.id("switch_price"));
                Select dropCurrency = new Select(selectCurrency);
                dropCurrency.selectByVisibleText(currency);
                break;
            }case "EUR":{
                WebElement selectCurrency = driver.findElement(By.id("switch_price"));
                Select dropCurrency = new Select(selectCurrency);
                dropCurrency.selectByVisibleText(currency);
                break;
            } default:{
                throw new NoSuchElementException("There are no currency: " + currency);
            }
        }
    }

    private void selectDepartureCity(String departureCity){
        switch (departureCity){
            case "Київ":{
                break;
            }case "Львів":{
                WebElement departure = driver.findElement(By.name("departure_city"));
                Select dropDeparture = new Select(departure);
                dropDeparture.selectByVisibleText("Львов");
                break;
            }default:{
                throw new NoSuchElementException("There is no city: " + departureCity);
            }
        }

    }

    private void search(){
        WebElement radioButtonCountOfShowPages = driver.findElement(By.id("items_per_page100"));
        radioButtonCountOfShowPages.click();
        WebElement buttonSubmit = driver.findElement(By.xpath("//input[@value='Найти' and @type='button']"));
        buttonSubmit.click();
    }

    public void driverQuit(){
        driver.quit();
    }

    public void addAllWebElementsToWebElementList(){
        ArrayList<WebElement> oddList = (ArrayList<WebElement>) driver.findElements(By.className("itt_odd"));
        ArrayList<WebElement> evenList = (ArrayList<WebElement>) driver.findElements(By.className("itt_even"));

        List<WebElement> webElementList = new ArrayList<>();
        //I do it to save same order of elements, as in search result
        for(int i = 0; i<evenList.size(); i++){
            webElementList.add(oddList.get(i));
            webElementList.add(evenList.get(i));
        }
        if(oddList.size()>evenList.size()){
            webElementList.add(oddList.get(oddList.size()-1));
        }
        oddList.clear();
        evenList.clear();
        for(WebElement webElement : webElementList){
            addTourToList(webElement);
        }

        for(int i = 2; i<100;i++){
            WebElement nextPage = null;
            try{
                nextPage = driver.findElement(By.linkText(String.valueOf(i)));
                nextPage.click();
            } catch (org.openqa.selenium.NoSuchElementException e){
                return;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            oddList = (ArrayList<WebElement>) driver.findElements(By.className("itt_odd"));
            evenList = (ArrayList<WebElement>) driver.findElements(By.className("itt_even"));

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
        }
    }

    public void addTourToList(WebElement webElement){
        List<String> tourDataList = new ArrayList<>();
        List<WebElement> listLeft = (ArrayList<WebElement>)webElement.findElements(By.className("itt_text-left"));
        List<WebElement> listCenter = (ArrayList<WebElement>)webElement.findElements(By.className("text-center"));
        WebElement textRight = webElement.findElement(By.className("text-right"));

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
      0  Хургада
      1  Princess Palace Hotel
      2  Std
      3  Киев
      4  3
      5  HB
      6  6
      7  14.12.14
      8  Подробнее
      9  1067€
         */

        Tour tour = new Tour();

        //set tour date
        //Поки-що дата туру буде датою вильоту :)
        String tourDate = tourDataList.get(7);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yy");
        Date date = null;
        try {
            date = dateFormat.parse(tourDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tour.setDate(date);

        //set tour days
        int days = Integer.parseInt(tourDataList.get(6));
        tour.setDays(days);

        //set departure city
        String depCity = tourDataList.get(3);
        if(depCity.equalsIgnoreCase("без перелета")) {
            tour.setDepartureCity(departureCity);
        } else {
            tour.setDepartureCity(depCity);
        }

        //set departure time
        String depTime = tourDataList.get(7);
        SimpleDateFormat depDateFormat = new SimpleDateFormat("dd.mm.yy");
        Date departureTime = null;
        try {
            departureTime = depDateFormat.parse(depTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tour.setDepartureTime(departureTime);

        String priceSt = tourDataList.get(9);
        char [] priceCh = priceSt.toCharArray();
        StringBuffer priceOnlyNumbers = new StringBuffer();
        for(char ch : priceCh){
            if(ch>=48 && ch <= 57){
                priceOnlyNumbers.append(ch);
            }
        }
        int priceInt = Integer.parseInt(priceOnlyNumbers.toString());
        BigDecimal price = new BigDecimal(priceInt);
        tour.setPrice(price);

        //set Hotel
        Country coun = new Country(country);
        Region reg = new Region(region, coun);
        int star = Integer.parseInt(tourDataList.get(4));
        Hotel hot = new Hotel(hotel, star, reg);
        tour.setHotel(hot);

        //set Food
        String foodSt = tourDataList.get(5);
        Food food = new Food(foodSt);
        tour.setFood(food);

        //add tour
        tourList.add(tour);
    }

    public static void main(String[] args) {
        int[] stars = {2, 3, 4, 5};
        String[] foods = {"HB", "AI"};
        int[] childrenAge = {9};
        //null for now
        TyrComUaParser parser = new TyrComUaParser("Туреччина", "Анталія", "Adalia Hotel", stars, foods, 3, 1, childrenAge,
                "01.10.14", "31.12.14", 6, 21, 4000, 12000, "Грн", "Львів");
        List<Tour> resultList = parser.parse();
        for(int i = 0; i<resultList.size(); i++){
            System.out.println(resultList.get(i).toString());
        }
        parser.driverQuit();
    }
}

