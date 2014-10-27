package com.softserveinc.softtour.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserveinc.softtour.parsers.TrainParser;

/**
 * @author Andrii
 * Has some utilities for class TrainParser
 */
public class TrainParserUtil {
	private static final String BASE_URL = "http://ticket.turistua.com/ua/train/reservation/?transport=train";
	private static final String UTF_8 = "UTF-8";
	
	private static final String CITY_CODE_VOCABULARY = "/parser_properties/city_code_vocabulary.properties";
	private static final String CITY_RU_UA_VOCABULARY = "/parser_properties/city_ru-ua_vocabulary.properties";

	private static final Logger LOG = LoggerFactory.getLogger(TrainParser.class);
	
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
		InputStream inputStream = null;
		BufferedInputStream bufferedInputStream = null;
		
		try {
			inputStream = 	this.getClass().getResourceAsStream(path);
			bufferedInputStream = new BufferedInputStream(inputStream);
			properties.load(new InputStreamReader(bufferedInputStream, UTF_8));
			
			if (properties.getProperty(city) == null) {
				return city ;
			}
			return properties.getProperty(city);
		
		} catch (UnsupportedEncodingException e) {
			LOG.error("The character encoding utf-8 is not supported.");
		} catch (IOException e) {
			LOG.error("Error of reading the file" + path  + ",  or file with the specified path not found");
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (bufferedInputStream != null) {
					bufferedInputStream.close();
				}
			} catch (IOException e) {
				LOG.error("Error of closing the stream ");
			}
		}
		return null;
	}
}