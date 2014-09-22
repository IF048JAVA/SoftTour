package com.softserveinc.softtour.parsers;

import com.softserveinc.softtour.entity.Tour;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

/**
 * Created by oleksandrgasenuk on 20.09.14.
 */

public class TyrComUaParser {
    private WebDriver driver = new HtmlUnitDriver(true);
    private static final String URL = "http://www.tyr.com.ua/tours/search.php";

    public TyrComUaParser() {
        driver.get(URL);
    }

    public TyrComUaParser(WebDriver driver) {
        this.driver = driver;
        driver.get(URL);
    }

    public List<Tour> parse(String country, String region, String hotel, int[] stars, String[] foods, int adults,
                int children, int[] childrenAge, String dateFlyFrom, String dateFlyTo, int countNightsFrom,
                int countNightsTo, int priceFrom, int priceTo, String currency, String departureCity){
        selectCountry(country);
        selectRegion(country, region);
        selectHotel(country, region, hotel);
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
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addAllWebElementsToWebElementList();


        //wait for 5 seconds



        //create list of ?
        return null;
    }

    private void selectCountry(String country){
        //Египет - default
        switch(country){
              case "Египет":{
                break;
            } case "Туреччина":{
                Select dropCountry = new Select(driver.findElement(By.id("itt_country")));
                dropCountry.selectByVisibleText("Турция");
                break;
            } case "Греція":{
                Select dropCountry = new Select(driver.findElement(By.id("itt_country")));
                dropCountry.selectByVisibleText("Греция");
                break;
            } default:{
                throw new NoSuchElementException("There is no country named " + country);
            }
        }

    }

    private void selectRegion(String country, String region){
        //Египет - default
        switch(country) {
            case "Египет": {
                switch (region) {
                    case "Дахаб": {
                        WebElement selectRegion = driver.findElement(By.id("region_list"));
                        Select dropRegion = new Select(selectRegion);
                        dropRegion.selectByVisibleText(region);
                        break;
                    }
                    case "Макаді Бей": {
                        WebElement selectRegion = driver.findElement(By.id("region_list"));
                        Select dropRegion = new Select(selectRegion);
                        dropRegion.selectByVisibleText("Макади Бей");
                        break;
                    }
                    case "Марса Алам": {
                        WebElement selectRegion = driver.findElement(By.id("region_list"));
                        Select dropRegion = new Select(selectRegion);
                        dropRegion.selectByVisibleText(region);
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case "Туреччина": {
                switch (region) {
                    default: {
                        break;
                    }
                }
                break;
            }
            case "Греція": {
                switch (region) {
                    default: {
                        break;
                    }
                }
                break;
            }
            default: {
                throw new NoSuchElementException("There is no country named " + country);
            }
        }
    }

    private void selectStars(int...stars){
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
            WebElement radioButtonStars2 = driver.findElement(By.xpath("//input[@value='7' and @name='hotel_rating_list']"));
            radioButtonStars2.click();
        }
        if (!starsBoolean[1]){
            WebElement radioButtonStars3 = driver.findElement(By.xpath("//input[@value='3' and @name='hotel_rating_list']"));
            radioButtonStars3.clear();
        }
        if (!starsBoolean[2]){
            WebElement radioButtonStars4 = driver.findElement(By.xpath("//input[@value='4' and @name='hotel_rating_list']"));
            radioButtonStars4.clear();
        }
        if (!starsBoolean[3]){
            WebElement radioButtonStars5 = driver.findElement(By.xpath("//input[@value='78' and @name='hotel_rating_list']"));
            radioButtonStars5.clear();
        }
    }

    private void selectHotel(String country, String region, String hotel){
        NoSuchElementException noSuchElementException = new NoSuchElementException("There is no such hotel " + hotel);
        switch(country) {
            case "Египет": {
                switch (region) {
                    case "Дахаб": {
                        switch (hotel){
                            case "AA Grand Oasis Resort (ex.Tropicana Grand Oasis Resort)" :{
                                WebElement selectHotel = driver.findElement(By.id("hotel_list"));
                                Select dropHotel = new Select(selectHotel);
                                dropHotel.selectByVisibleText(hotel);
                                break;
                            }
                            default:{
                                return;
                            }
                        }
                    }
                    case "Макаді Бей": {
                        switch (hotel){
                            default:{
                                return;
                            }
                        }
                    }
                    case "Марса Алам": {
                        switch (hotel){
                            default:{
                                return;
                            }
                        }
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case "Туреччина": {
                switch (region) {
                    default: {
                        break;
                    }
                }
            }
            case "Греція": {
                switch (region) {
                    default: {
                        break;
                    }
                }
            }
            default: {
                throw new NoSuchElementException("There is no country named " + country);
            }
        }
    }

    private void selectFood(String...foods){
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
        WebElement dataFlyFrom = driver.findElement(By.id("itt_date_from"));
        dataFlyFrom.clear();
        dataFlyFrom.sendKeys(dateFlyFrom);
    }

    private void selectDateFlyTo(String dateFlyTo){
        WebElement dataFlyTo = driver.findElement(By.id("date_till"));
        dataFlyTo.clear();
        dataFlyTo.sendKeys(dateFlyTo);
    }

    private void selectCountNightsFrom(int countNightsFrom){
        if(countNightsFrom == 6){
            return;
        }
        if(countNightsFrom <= 0 || countNightsFrom > 21){
            throw new NoSuchElementException("Count nights must be more then 0 and less then 22. It is " +
                                             countNightsFrom);
        }
        WebElement nightsFrom = driver.findElement(By.id("night_from"));
        Select dropNightsFrom = new Select(nightsFrom);
        dropNightsFrom.selectByVisibleText(String.valueOf(countNightsFrom));
    }

    private void selectCountNightsTo(int countNightsTo){
        if(countNightsTo == 14){
            return;
        }
        if(countNightsTo <=0 || countNightsTo > 21){
            throw new NoSuchElementException("Count nights must be more then 0 and less then 22. It is " +
                                             countNightsTo);
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
        if(priceTo == 99000){
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
            }case "грн":{
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
            System.out.println(webElement.getText());
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
                System.out.println(webElement.getText());
            }
            oddList.clear();
            evenList.clear();
        }

    }

    public static void main(String[] args) {
        TyrComUaParser parser = new TyrComUaParser();
        int[] stars = {3,4,5};
        String[] foods = {"HB", "AI"};
        int[] childrenAge = {9};
        //null for now
        List<Tour> resultList = parser.parse("Египет", "Мухосранськ", "Каліфорнія", stars, foods, 3, 1, childrenAge,
                "01.10.14", "31.12.14", 6, 21, 400, 1070,"EUR", "Київ");

        parser.driverQuit();
    }
}

