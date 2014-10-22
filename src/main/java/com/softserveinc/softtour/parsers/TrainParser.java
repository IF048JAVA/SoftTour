package com.softserveinc.softtour.parsers;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.softserveinc.softtour.bean.TrainRoute;
import com.softserveinc.softtour.util.NoRoutesException;
import com.softserveinc.softtour.util.TrainParserUtil;
import com.softserveinc.softtour.util.DateValidator;

/**
 * @author Andrii
 * Parses the site http://ticket.turistua.com/
 */
public class TrainParser {
	private static final int MAX_NUMER_OF_ATTEMPTS = 15;
	private static final int CONNECTION_TIMEOUT = 5000; 
	
	private String url;
	private String departureDate;
	private String departureTime;
	private String previousDate;
	
	private TrainRoute trainRoute;
	private ArrayList<TrainRoute> routesList;
	private TrainParserUtil trainParserUtil;
	private DateValidator dateValidator;
	
	private boolean isSetDepatureDate = true;
	private boolean isSetPreviousDate = true;
	
	/**
	 * Sets departureDate and departureTime.
	 * Creates objects for classes TrainParserUtil and DateValidator
	 * Creates URL with the specified parameters for parsing site http://ticket.turistua.com/
	 * 
	 * @param departureCity - it's a city of departure of the train
	 * @param arrivalCity - it's a city of arrival of the train
	 * @param departureDate - it's a date of departure of the plane
	 * @param departureTime - it's a time of departure of the plane
	 */
	public TrainParser(String departureCity, String arrivalCity, String departureDate, String departureTime) {
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		
		trainParserUtil = new TrainParserUtil();
		dateValidator = new DateValidator();
		routesList = new ArrayList<TrainRoute>();
		
		url = trainParserUtil.createUrl(departureCity, arrivalCity, departureDate);
	}
	
	/**
	 * @return the list of the routes with the specified parameters 
	 * or empty list if the routes no found
	 */
	public ArrayList<TrainRoute> getRoutes() {
		try {
			parse();
			
			isSetDepatureDate = false;
			parse();
		} catch (NoRoutesException e) {
			// TODO Add logging here: WARN/ERROR 
		}
		return routesList;
	}
	
	/**
	 * Parses the site http://ticket.turistua.com/ 
	 * @throws NoRoutesException if the routes no found
	 */
	public void parse() throws NoRoutesException {
		Document document = null;
		
		int i = 0;
		while (i < MAX_NUMER_OF_ATTEMPTS) {
			++i;
			try {
				document = Jsoup.connect(url).timeout(CONNECTION_TIMEOUT).get();
			} catch (IOException e) {
				// TODO Add logging here: WARN - Invalid attempt to connect. Trying one more ...
				continue;
			}
			break;
		}
		parseRoutes(document);
	}
		
	/**
	 * Parses the routes with the given document
	 * @param document - it's document, which will be parsed
	 * @throws NoRoutesException if the routes no found
	 */
	private void parseRoutes(Document document) throws NoRoutesException {
		Element routesTable = null;
		
		try{
			routesTable = document.getElementsByTag("body").get(0)
				.getElementById("turistua_site")
				.getElementById("content")
				.getElementsByClass("reservationCol").get(0)
				.getElementsByClass("ticketsBox").get(0)
				.getElementById("t2t_tableBox")
				.getElementById("t2t_tables")
				.getElementsByTag("tbody").get(0);
			
			Elements routes = routesTable.select("tr[class]");
			parseRoute(routes);
		}catch(NullPointerException e){
			throw new NoRoutesException();
		}
	}
	
	/**
	 * Parses the route with the given routes
	 * @param routes - it's routes, which will be parsed
	 */
	private void parseRoute(Elements routes) {
		for (Element route : routes) {
			createTrainRouteObject();
			
			Elements unitsRoute = route.getElementsByTag("td");
			parseUnitRoute(unitsRoute);
			
			boolean passingValidation = dateValidator.validate(trainRoute, departureTime);
			if (passingValidation) {
				addRoute();
			}
		}
	}

	/**
	 * Creates new object of the class TrainRoute
	 * in which we will be write the data
	 */
	private void createTrainRouteObject() {
		trainRoute = new TrainRoute();
		
		if (isSetDepatureDate) {
			trainRoute.setDepartureDate(departureDate);
		}else {
			if (isSetPreviousDate) {
				previousDate = dateValidator.setPreviousDate();
				isSetPreviousDate = false;
			}
			trainRoute.setDepartureDate(previousDate);
		}
	}

	/**
	 * Parses the unit of the route with the given units of the one route
	 * @param unitsRoute - it's units of the one route, which will be parsed
	 */
	private void parseUnitRoute(Elements unitsRoute) {
		int unitNumber = 0;
		trainRoute.setId(unitsRoute.get(unitNumber).text());
		trainRoute.setDepartureCity(unitsRoute.get(++unitNumber).text());
		trainRoute.setArrivalCity(unitsRoute.get(++unitNumber).text());
		trainRoute.setDepartureTime(unitsRoute.get(++unitNumber).text());
		trainRoute.setOnWayTime(unitsRoute.get(++unitNumber).text());
		trainRoute.setArrivalTime(unitsRoute.get(++unitNumber).text());
		
		parsePrices(unitsRoute, unitNumber);
	}

	/**
	 * Parses the prices of the route with the given units of the one route 
	 * @param unitsRoute - it's units of the one route, which will be parsed
	 * @param unitNumber - it's a number of the one unit of the route
	 */
	private void parsePrices(Elements  unitsRoute, int unitNumber) {
		int unitsSize = unitsRoute.size();
		String[] pricesRoute = new String[unitsSize-(unitNumber+1)];
		
		for (int j = 0; j < pricesRoute.length; j++) {
			Element unitRoutePrise = unitsRoute.get(++unitNumber);
			pricesRoute[j] = parsePrice(unitRoutePrise);
		}
		
		boolean isMaxSet = false;
		for (int i = 0; i < pricesRoute.length; i++) {
			if (pricesRoute[i] == null) {
				continue;
			}
			if (!isMaxSet) {
				trainRoute.setPriceMax(pricesRoute[i]);
				isMaxSet = true;
			}
			trainRoute.setPriceMin(pricesRoute[i]);
		}
	}
	
	/**
	 * Parses the price of the route with the given unitRoutePrise
	 * @param unitRoutePrise - it's unit of the one route, which described the price and which will be parsed
	 * @return the price of the route
	 */
	private String parsePrice(Element unitRoutePrise){
		String price = null;
		Elements unitsPrice = unitRoutePrise.getElementsByTag("span");
		if (!unitsPrice.isEmpty()){
			price = unitsPrice.get(0).text();
		}
		return price;
	}
	
	/**
	 * Adds route to the list of the routes
	 */
	private void addRoute() {
		String departureCityUa = trainParserUtil.translateCity(trainRoute.getDepartureCity());
		trainRoute.setDepartureCity(departureCityUa);
		String arrivalCityUa = trainParserUtil.translateCity(trainRoute.getArrivalCity());
		trainRoute.setArrivalCity(arrivalCityUa);
		
		routesList.add(trainRoute);
	}

	/**
	 * Only for testing
	 */
	public static void main(String[] args) {
		//TODO DELETE !
		TrainParser obj = new TrainParser("Київ", "Львів", "2014-11-08", "23:00");
		ArrayList<TrainRoute> routesList = obj.getRoutes();
		
		for (TrainRoute route : routesList) {
			System.out.println(route);
		}
	}
}