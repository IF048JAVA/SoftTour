package com.softserveinc.softtour.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Tests the TrainParserUtil class
 * @author Andrii
 */
@Test
@ContextConfiguration(locations = {"/spring-test-config.xml", 
		"/spring-data.xml",
		"/applicationContext.xml",
		"/mvc-dispatcher-servlet.xml",
		"/spring-security.xml",
		"/spring-mail.xml"})
@WebAppConfiguration
public class TestTrainParserUtil extends AbstractTestNGSpringContextTests{
	
	@Autowired
	TrainParserUtil trainParserUtil;
	
	/**
	 * Tests the method translateCity of the TrainParserUtil class
	 * Checks if this method right translates the names of the city 
	 * from Russian to Ukrainian  
	 */
	@Test
	public void testTranslateCity() {
		String city = "Ивано-франковск";
		String expectedCity = "Івано-Франківськ";
		String actualCity = trainParserUtil.translateCity(city);
	
		assertNotNull(actualCity);
		assertEquals(actualCity, expectedCity);
	}
	
	/**
	 * Tests the method createUrl of the TrainParserUtil class
	 * Checks if this method right creates url for the specify parameters
	 */
	@Test
	public void testCreateUrl() {
		String BASE_URL = "http://ticket.turistua.com/ua/train/reservation/?transport=train";
		String expectedUrl = BASE_URL + "&dt=2014-11-10&src=22200001&dst=22218000";
		String departureCity = "Київ";
		String arrivalCity ="Львів";
		String departureDate = "2014-11-10";
		
		String actualUrl = trainParserUtil.createUrl(departureCity, arrivalCity, departureDate);
		
		assertNotNull(actualUrl);
		assertEquals(actualUrl, expectedUrl);
	}
	
}