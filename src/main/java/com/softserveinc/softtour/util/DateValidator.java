package com.softserveinc.softtour.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.softserveinc.softtour.bean.TrainRoute;

public class DateValidator {
	
	private Date depatureDateTimePlane;
	
	private SimpleDateFormat dateTimeFormat;
	private SimpleDateFormat dateFormat;
	private Calendar calendar;
	
	private boolean isSet = false;
	
	public DateValidator() {
		dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		calendar = Calendar.getInstance();
	}
	
	public boolean validate(TrainRoute trainRoute, String departureTime) {
		String depatureDateTimeTrain = trainRoute.getDepartureDate() + " "	+ trainRoute.getDepartureTime();
		String onWayTime = trainRoute.getOnWayTime();

		setDepatureDateTimePlane(trainRoute,  departureTime); 
		
		try {
			calendar.setTime(dateTimeFormat.parse(depatureDateTimeTrain));
			// Настроюю час приїзду поїзда
			calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(onWayTime.substring(1, 3)));
			calendar.add(Calendar.MINUTE, Integer.parseInt(onWayTime.substring(4, 6)));
			int differenceHours = (int) (depatureDateTimePlane.getTime() - calendar.getTime().getTime()) / 3600000;

			if (differenceHours >= 3) {
				return true;
			} 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String setPreviousDate(TrainRoute trainRoute,  String departureTime) {
		Date departureDate = null;
		setDepatureDateTimePlane(trainRoute,  departureTime); 
		
		try {
			departureDate = dateFormat.parse(trainRoute.getDepartureDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(departureDate);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		String previousDate = dateFormat.format(calendar.getTime());

		return previousDate;
	}

	private void setDepatureDateTimePlane(TrainRoute trainRoute,  String departureTime) {
		if (!isSet) {
			try {
				depatureDateTimePlane = dateTimeFormat.parse(trainRoute.getDepartureDate() + " " + departureTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			isSet = true;
		}
	}

}