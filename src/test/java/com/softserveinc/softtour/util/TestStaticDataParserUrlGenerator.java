package com.softserveinc.softtour.util;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestStaticDataParserUrlGenerator {
    private Pattern pattern =
            Pattern.compile("^(?:(?:https?|ftp|telnet)://(?:[a-z0-9_-]{1,32}(?::[a-z0-9_-]{1,32})?@)?)?(?:(?:[a-z0-9-]{1,128}\\.)+(?:ru|su|com|net|org|mil|edu|arpa|gov|biz|info|aero|inc|name|[a-z]{2})|(?!0)(?:(?!0[^.]|255)[0-9]{1,3}\\.){3}(?!0|255)[0-9]{1,3})(?:/[a-zA-Z0-9.,_@%&?+=\\~/-]*)?(?:#[^ '\\\"&]*)?$");
    private Matcher matcher;
    private Random random;

    @BeforeTest
    public void before(){
        random = new Random();
    }

    @Test
    public void testValidCountryUrl(){
        String countryUrl = StaticDataParserUrlGenerator.createCountryUrl();
        matcher = pattern.matcher(countryUrl);
        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testValidRegionUrl(){
        long countryCode = random.nextLong();
        String regionUrl = StaticDataParserUrlGenerator.createRegionUrl(countryCode);
        matcher = pattern.matcher(regionUrl);
        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testValidHotelUrl(){
        long countryCode = random.nextLong();
        long regionCode = random.nextLong();
        String regionUrl = StaticDataParserUrlGenerator.createHotelUrl(countryCode, regionCode);
        matcher = pattern.matcher(regionUrl);
        Assert.assertTrue(matcher.matches());
    }
}
