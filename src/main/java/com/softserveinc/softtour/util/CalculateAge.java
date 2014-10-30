package com.softserveinc.softtour.util;

import java.sql.Date;
import java.util.Calendar;

/**
 * @author Andriy
 *
 */
public class CalculateAge {

	/**
	 * Calculates the age of user
	 * @param birthday - it's user's birthday 
	 * @return the age of user
	 */
	public byte calculateAge(Date birthday){
		Calendar day = Calendar.getInstance();
	    Calendar today = Calendar.getInstance();
	 
	    day.setTime(birthday);
	    // include day of birth
	    day.add(Calendar.DAY_OF_MONTH, -1);
	 
	   byte age = (byte) (today.get(Calendar.YEAR) - day.get(Calendar.YEAR));
	  
	   if (today.get(Calendar.DAY_OF_YEAR) < day.get(Calendar.DAY_OF_YEAR)) {
	       return age--;
	    }
	   return age;
	}
}
