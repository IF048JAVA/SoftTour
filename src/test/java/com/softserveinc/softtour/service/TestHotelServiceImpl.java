package com.softserveinc.softtour.service;

import com.softserveinc.softtour.entity.Hotel;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Test
@ContextConfiguration(locations = {"/spring-test-config.xml",
        "/spring-data.xml",
        "/applicationContext.xml",
        "/mvc-dispatcher-servlet.xml",
        "/spring-security.xml",
        "/spring-mail.xml"})

@WebAppConfiguration
public class TestHotelServiceImpl extends AbstractTestNGSpringContextTests {

    private static final long expectedCount = 2;
    private static final long expectedSearchResult = 3;
    private static final long DAHAB_ID = 5;
    private static final long ANTALIA_ID = 19;
    private static final long ATHENS_ID = 43;

    Hotel testHotel1;
    Hotel testHotel2;
    Hotel testHotel3;
    List<String> countries = new ArrayList<String>();
    Pageable pageable;

    @Autowired
    HotelService hotelService;

    @Autowired
    RegionService regionService;

    @BeforeClass
    public void setTestData() {

        testHotel1 = new Hotel("TestHotel1", 4, 10, BigDecimal.valueOf(4.3), BigDecimal.valueOf(4.1),
                BigDecimal.valueOf(4.9), BigDecimal.valueOf(4.1), BigDecimal.valueOf(4.2), "", (long) 1,
                regionService.findOne(DAHAB_ID));

        testHotel2 = new Hotel("TestHotel2", 4, 10, BigDecimal.valueOf(4.2), BigDecimal.valueOf(3.7),
                BigDecimal.valueOf(4.6), BigDecimal.valueOf(4.5), BigDecimal.valueOf(4), "", (long) 1,
                regionService.findOne(ANTALIA_ID));

        testHotel3 = new Hotel("TestHotel3", 4, 10, BigDecimal.valueOf(3), BigDecimal.valueOf(2.6),
                BigDecimal.valueOf(3), BigDecimal.valueOf(2.9), BigDecimal.valueOf(3.5), "", (long) 1,
                regionService.findOne(ATHENS_ID));

        countries.add("Єгипет");
        countries.add("Греція");
        countries.add("Туреччина");
        countries.add("ОАЕ");

        pageable = new PageRequest(0, 10);
    }


    @Test
    public void testSave() {

        hotelService.save(testHotel1);
        hotelService.save(testHotel2);
        hotelService.save(testHotel3);

        Hotel actualHotel = hotelService.findByName("TestHotel1");

        assertEquals(actualHotel, testHotel1);
    }

    @Test(dependsOnMethods = {"testSave"})
    public void testSearchHotel(){

        Page<Hotel> searchResult = hotelService.searchHotel("test", pageable);

        long actualCount = searchResult.getTotalElements();

        assertEquals(actualCount, expectedSearchResult);
    }

    @Test(dependsOnMethods = {"testSearchHotel"})
    public void testFindByCustomParameters() {

        Page<Hotel> searchResult = hotelService.findByCustomParameters(countries, BigDecimal.valueOf(4),
                BigDecimal.valueOf(3.5), BigDecimal.valueOf(4.5), BigDecimal.valueOf(4), BigDecimal.valueOf(4), pageable);

        long actualCount = searchResult.getTotalElements();

        assertEquals(actualCount, expectedCount);
    }


    @Test(dependsOnMethods = {"testFindByCustomParameters"})
    public void deletehotel(){

        hotelService.delete(testHotel1.getId());
        hotelService.delete(testHotel2.getId());
        hotelService.delete(testHotel3.getId());

        assertNull(hotelService.findOne(testHotel1.getId()));
        assertNull(hotelService.findOne(testHotel2.getId()));
        assertNull(hotelService.findOne(testHotel3.getId()));
    }

}
