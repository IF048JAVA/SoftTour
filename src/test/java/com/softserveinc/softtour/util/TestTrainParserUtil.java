package com.softserveinc.softtour.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
@ContextConfiguration(locations = {"/spring-test-config.xml", 
		"/WEB-INF/spring-data.xml",
		"/WEB-INF/applicationContext.xml",
		"/WEB-INF/mvc-dispatcher-servlet.xml",
		"/WEB-INF/spring-security.xml",
		"/WEB-INF/spring-mail.xml"})
@WebAppConfiguration
public class TestTrainParserUtil extends AbstractTestNGSpringContextTests{
	
	@Autowired
	TrainParserUtil trainParserUtil;
	
	@Test
	public void testTranslateCity() {
		String city = "Ивано-франковск";
		String expectedCity = "Івано-Франківськ";
		String actualCity = trainParserUtil.translateCity(city);
	
		assertNotNull(actualCity);
		assertEquals(actualCity, expectedCity);
	}
	
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