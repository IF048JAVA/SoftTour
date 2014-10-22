package com.softserveinc.softtour.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author Andrii
 * Has some utilities for class TrainParser
 */
public class TrainParserUtil {
	private static final String BASE_URL = "http://ticket.turistua.com/ua/train/reservation/?transport=train";
	
	// FIXME change url for testing src/main/resources
	private static final String CITY_CODE_VOCABULARY = "/parser_properties/city_code_vocabulary.properties";
	private static final String CITY_RU_UA_VOCABULARY = "/parser_properties/city_ru-ua_vocabulary.properties";
	
	/**
	 * Creates the url with the specified parameters
	 * @param departureCity - it's a city of departure of the train
	 * @param arrivalCity - it's a city of arrival of the train
	 * @param departureDate - it's a date of departure of the train
	 * @return the url for site http://ticket.turistua.com with the specified parameters
	 */
	public String createUrl(String departureCity, String arrivalCity,
			String departureDate) {
		StringBuilder baseUrl = new StringBuilder(BASE_URL);

		String url = baseUrl.append("&dt=").append(departureDate)
							.append("&src=").append(getCityInfo(departureCity, CITY_CODE_VOCABULARY))
							.append("&dst=").append(getCityInfo(arrivalCity, CITY_CODE_VOCABULARY))
							.toString();
		return url;
	}

	/**
	 * Translates the given city from Russian to Ukrainian 
	 * @param city - it's city, which will be translated
	 * @return Ukrainian city name. 
	 * If the Ukrainian city name wasn't found return current Russian city name.
	 */
	public String translateCity(String city) {
		return getCityInfo(city, CITY_RU_UA_VOCABULARY);
	}
	
	/**
	 * Returns the information about the city such as code or translation of the specified city
	 * @param city - it's city, whose code or translation will be returned 
	 * @param path - it's path to the property file which contains the map of the cities codes or cities translations
	 * @return the code or translation of the specified city 
	 */
	private String getCityInfo(String city, String path) {
		Properties properties = new Properties();
		
		InputStream inputProperties = this.getClass().
                  getResourceAsStream(path);
		try {
			   properties.load(new InputStreamReader(inputProperties));
			 
				if (properties.getProperty(city) == null) {
					return city ;
				}
				
				return properties.getProperty(city);
			   
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//TODO Only for testing !!!
	public static void main(String[] args) {
		TrainParserUtil trainParserUtil = new TrainParserUtil();
	
		System.out.println(trainParserUtil.getCityInfo("Дніпропетровськ", CITY_CODE_VOCABULARY));
		System.out.println(trainParserUtil.translateCity("Днепропетровск главный"));
	}
	
}