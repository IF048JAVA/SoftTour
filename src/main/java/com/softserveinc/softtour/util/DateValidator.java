package com.softserveinc.softtour.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserveinc.softtour.bean.TrainRoute;
import com.softserveinc.softtour.parsers.TrainParser;

/**
 * @author Andrii
 * Validates a date and time of arrival of the train
 */
public class DateValidator {
	private static final int HOURS_BEFOERE_FLIGHT = 3;
	private static final int NUMBER_MILLISECONDS_IN_HOUR = 3600000;
	
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	private static final Logger LOG = LoggerFactory.getLogger(TrainParser.class);
	
	private Date depatureDateTimePlane;
	private TrainRoute trainRoute;
	
	private SimpleDateFormat dateTimeFormat;
	private SimpleDateFormat dateFormat;
	private Calendar calendar;
	
	private boolean isDepatureDateTimeSet = false;
	
	/**
	 * Creates the objects of the classes SimpleDateFormat and Calendar
	 */
	public DateValidator() {
		dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
		dateFormat = new SimpleDateFormat(DATE_FORMAT);
		calendar = Calendar.getInstance();
	}
	
	/**
	 * Checks a date and time of arrival of the train
	 * If the difference between the time of arrival of the train and 
	 * time of departure the plane at least three hours then return true else return false
	 * 
	 * @param trainRoute - it's object, which contains the information about route 
	 * @param departureTime - it's a time of departure of the plane
	 * @return true if the difference between the time of arrival of the train and 
	 * time of departure the plane at least three hours, else return false
	 */
	public boolean validate(TrainRoute trainRoute, String departureTime) {
		this.trainRoute = trainRoute;
		String depatureDateTimeTrain = trainRoute.getDepartureDate() + " "	+ trainRoute.getDepartureTime();
		String onWayTime = trainRoute.getOnWayTime();

		try {
			// Sets date and time of arrival of the train  
			calendar.setTime(dateTimeFormat.parse(depatureDateTimeTrain));
			calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(onWayTime.substring(1, 3)));
			calendar.add(Calendar.MINUTE, Integer.parseInt(onWayTime.substring(4, 6)));
			
			if (!isDepatureDateTimeSet) {
				// Sets date and time of departure of the plane
				depatureDateTimePlane = dateTimeFormat.parse(trainRoute.getDepartureDate() + " " + departureTime);
				isDepatureDateTimeSet = true;
			}

			int difference = (int) (depatureDateTimePlane.getTime() - calendar.getTime().getTime()) / NUMBER_MILLISECONDS_IN_HOUR;
			if (difference >= HOURS_BEFOERE_FLIGHT) {
				return true;
			} 
		} catch (ParseException e) {
			LOG.error("Error of parsing the date.");
		}
		return false;
	}

	/**
	 * Sets previous date to the date of arrival of the train
	 * @return the previous date
	 */
	public String setPreviousDate() {
		Date departureDate = null;
		try {
			departureDate = dateFormat.parse(trainRoute.getDepartureDate());
		} catch (ParseException e) {
			LOG.error("Error of parsing the date.");
		}
		
		calendar.setTime(departureDate);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		String previousDate = dateFormat.format(calendar.getTime());

		return previousDate;
	}

}