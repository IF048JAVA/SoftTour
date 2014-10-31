package com.softserveinc.softtour.util;

import com.softserveinc.softtour.entity.Country;
import com.softserveinc.softtour.entity.Hotel;
import com.softserveinc.softtour.entity.Region;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestItTourParserUrlGenerator {
    private Pattern pattern =
            Pattern.compile("^(?:(?:https?|ftp|telnet)://(?:[a-z0-9_-]{1,32}(?::[a-z0-9_-]{1,32})?@)?)?(?:(?:[a-z0-9-]{1,128}\\.)+(?:ru|su|com|net|org|mil|edu|arpa|gov|biz|info|aero|inc|name|[a-z]{2})|(?!0)(?:(?!0[^.]|255)[0-9]{1,3}\\.){3}(?!0|255)[0-9]{1,3})(?:/[a-zA-Z0-9.,_@%&?+=\\~/-]*)?(?:#[^ '\\\"&]*)?$");
    private Matcher matcher;
    private Random random;

    @BeforeTest
    public void before(){
        random = new Random();
    }

    @Test
    public void testValidQuickSearchUrl(){
        long countryParam = random.nextLong();
        int adults = random.nextInt();
        int children = random.nextInt();
        int priceFrom = random.nextInt();
        int priceTo = random.nextInt();
        int pageNumber = random.nextInt();
        String quickSearchUrl = ItTourParserUrlGenerator.createQuickSearchUrl(countryParam, adults, children, priceFrom,
                priceTo, pageNumber);
        matcher = pattern.matcher(quickSearchUrl);
        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testValidAdvanceSearchUrl(){
        long countryParam = random.nextLong();
        long regionParam = random.nextLong();
        Set<Integer> hotelStars = new HashSet<>();
            hotelStars.add(random.nextInt());
            hotelStars.add(random.nextInt());
        Set<String> food = new HashSet<>();
            food.add("first");
            food.add("second");
        int adults = random.nextInt();
        int children = random.nextInt();
        String dateFrom = "dd.MM.yy";
        String dateTo = "dd.MM.yy";
        int nightsFrom = random.nextInt();
        int nightsTo = random.nextInt();
        int priceFrom = random.nextInt();
        int priceTo = random.nextInt();
        int pageNumber =random.nextInt();
        String advanceSearchUrl = ItTourParserUrlGenerator.createAdvanceSearchUrl(countryParam, regionParam, hotelStars,
                food, adults, children, dateFrom, dateTo, nightsFrom, nightsTo, priceFrom, priceTo, pageNumber);
        matcher = pattern.matcher(advanceSearchUrl);
        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testValidSearchUrlByHotel(){
        String hotelName = "hotel";
        int hotelStars = random.nextInt();
        long countryItTourId = random.nextInt();
        long regionItTourId =random.nextInt();
        long hotelItTourId = random.nextInt();
        Country country = new Country("country", countryItTourId);
        Region region = new Region("region", regionItTourId, country);
        Hotel hotel = new Hotel(hotelName, hotelStars, region);
        hotel.setItTourId(hotelItTourId);
        int pageNumber =random.nextInt();
        String searchUrlByHotel = ItTourParserUrlGenerator.createSearchUrlByHotel(hotel, pageNumber);
        matcher = pattern.matcher(searchUrlByHotel);
        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testAdvanceDataUrl(){
        String[] tourItTourId = new String[2];
        tourItTourId[0] = "first_id";
        tourItTourId[1] = "second_id";
        String advanceDataUrl = ItTourParserUrlGenerator.createAdvanceDataUrl(tourItTourId);
        matcher = pattern.matcher(advanceDataUrl);
        Assert.assertTrue(matcher.matches());
    }
}
