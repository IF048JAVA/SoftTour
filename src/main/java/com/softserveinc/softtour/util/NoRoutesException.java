package com.softserveinc.softtour.util;

/**
 * @author Andrii
 * Generates the exception when the routes wasn't find
 * Describes the exception
 */
@SuppressWarnings("serial")
public class NoRoutesException extends RuntimeException {

	public NoRoutesException() {
		super("No routes found!");
	}
}
