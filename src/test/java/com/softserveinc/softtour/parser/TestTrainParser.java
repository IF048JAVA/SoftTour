package com.softserveinc.softtour.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*; 

import com.softserveinc.softtour.bean.TrainRoute;
import com.softserveinc.softtour.parsers.TrainParser;

/**
 * @author Andrii
 * Tests the TrainParser class
 */
@Test
@ContextConfiguration(locations = {"/spring-test-config.xml", 
									"/spring-data.xml",
									"/applicationContext.xml",
									"/mvc-dispatcher-servlet.xml",
									"/spring-security.xml",
									"/spring-mail.xml"})
@WebAppConfiguration
public class TestTrainParser extends AbstractTestNGSpringContextTests{
	private static final Logger LOG = LoggerFactory.getLogger(TrainParser.class);
	
	private static final String PATH = "/TuristUA.html";
	private static final String UTF_8 = "UTF-8";
	private static final String BASE_URL = "http://ticket.turistua.com/";

	@Autowired
	private TrainParser trainParser;
	
	private Document document;
	
	private ArrayList<TrainRoute> routesList;
	private TrainRoute expectedTrainRoute1;
	private TrainRoute expectedTrainRoute2;
	
	/**
	 * Parses the html file, which contains the search result of the routes with the specify parameters:
	 *  departure city - "Kyiv", 
	 *  arrival city - "Lviv",
	 *  departure date of the train - "2014-11-11",
	 *  departure time of the plain - "17:00"
	 */
	@BeforeClass
	private void parseFile(){
		InputStream inputStream = null; 
		try {
			inputStream = this.getClass().getResourceAsStream(PATH);
			document = Jsoup.parse(inputStream, UTF_8, BASE_URL);
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}
	
	/**
	 * Creates the expected routes
	 */
	@BeforeClass
	private void createExpectedRoutes() {
		expectedTrainRoute1 = new TrainRoute("111О", "Харків", "Львів", 
				"2014-11-11", "04:08", "\u00a0"+"09:14", "13:22", "186.39", "618.15");
		
		expectedTrainRoute2 = new TrainRoute("741К", "Київ Дарниця", "Трускавець", 
				"2014-11-11", "06:48", "\u00a0"+"05:11", "11:59", "310.84", " - ");
	}
	
	/**
	 * Parses the document and checks if the search results equals expected results.
	 */
	@Test
	public void testParseRoutes(){
		assertNotNull(document);
		
		trainParser.parseRoutes(document);
		routesList = trainParser.getRoutesList(); 
		
		assertNotNull(routesList);
		assertEquals(routesList.size(), 2);
		assertTrue(routesList.contains(expectedTrainRoute1));
		assertTrue(routesList.contains(expectedTrainRoute2));
	}
	
}